package com.database.databasedemo.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import com.database.databasedemo.entity.Reservations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
public class ScheduledJob {
        @Autowired
        TimeService timeService;

        @Autowired
        ReservationService reservationService;
        private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Async
        @Scheduled(fixedRate = 10000)
        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void processAutomatedJobs() {
            //log.info("The time is now {}", dateFormat.format(new Date()));
           // log.info("The time form time service {}", timeService.getCurrentTime().getHour());
            OffsetDateTime currentTime=timeService.getCurrentTime();
            LocalDate endDate,startDate,currentDate,nextDate;
            DayOfWeek day,nextDay;
            currentDate=currentTime.toLocalDate();
            //List<Reservations> checkNoShow=reservationService.getReservationsForLateCheckIn(currentTime);
            List<Reservations> checkNoShow=reservationService.getReservationsForLateCheckIn();
            log.info("Query returned results for no show {}->",checkNoShow);
            for (Reservations r:checkNoShow) {
                if(r.getStatus().equals("Booked")) {
                    startDate = r.getStartDate().toLocalDate();
                    endDate = r.getEndDate().toLocalDate();
                    nextDate = startDate.plusDays(1);
                    nextDay = nextDate.getDayOfWeek();
                    day = startDate.getDayOfWeek();
                    int diff = nextDate.compareTo(currentDate);
                    if ((diff == 0 && currentTime.getHour() >= 3) || diff < 0) {
                        System.out.println("User hasn't showed up for reservation " + r.getId());

                        float penalty = 0;
                        boolean nextDayPenalty = false;

                        if (endDate.compareTo(startDate) > 1) {
                            nextDayPenalty = true;
                        }

                        if (day.toString().equals("SATURDAY") || day.toString().equals("SUNDAY")) {
                            penalty = (float) -(0.3 * r.getBookedPriceWeekend());
                            if (nextDayPenalty) {
                                penalty += (0.3 * r.getBookedPriceWeekend());
                            }
                        } else {
                            penalty = (float) -(0.3 * r.getBookedPriceWeekday());
                            if (nextDayPenalty) {
                                penalty += (0.3 * r.getBookedPriceWeekday());
                            }
                        }
                        if (nextDayPenalty) {
                            if (nextDay.toString().equals("SATURDAY") || nextDay.toString().equals("SUNDAY")) {
                                penalty += (0.3 * r.getBookedPriceWeekend());
                            } else {
                                penalty += (0.3 * r.getBookedPriceWeekday());
                            }
                        }
                        System.out.println(penalty);
                        r.setPenaltyValue(penalty);
                        r.setPenaltyReason("No Show");
                        r.setStatus("Available");
                        r.setPaymentAmount(penalty);
                    }
                }
            }
            //List<Reservations> autoCheckOut=reservationService.getReservationsToBeCheckedOut(timeService.getCurrentTime());
            List<Reservations> autoCheckOut=reservationService.getReservationsToBeCheckedOut();
            log.info("Query returned results for auto check out {}->",autoCheckOut);
            for (Reservations r:autoCheckOut) {
                endDate=r.getEndDate().toLocalDate();
                int diff=endDate.compareTo(currentDate);
                if(r.getStatus().equals("Payment Processed")) {
                    if ((diff < 0) || (diff == 0 && currentTime.getHour() > 11)) {
                        System.out.println("Checking out reservation id: " + r.getId());
                        r.setCheckOutDate(r.getEndDate());
                        r.setStatus("Available");
                    }
                }
            }
        }

}
