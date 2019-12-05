package com.database.databasedemo.service;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
import com.database.databasedemo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    @Qualifier("reservations")
    ReservationRepo reservationRepo;

    @Autowired
    PropertyService propertyService;
    @Autowired

    TimeService timeService;

    PersonSpringDataRepo personSpringDataRepo;
    @Autowired
    TimeService timeservice;


    public Reservations getReservation(int id) {
        return reservationRepo.findById(id).orElse(null);
    }

    public List<Reservations> getAllReservations() {
        return reservationRepo.findAll();
    }

    public List<Reservations> getGuestReservations(int id) {
        return reservationRepo.findByGuestId(id);
    }


//    public List<Reservations> getHostReservations(int guestId) {
//
//        return reservationRepo.findByGuestId(guestId);
//    }
    public List<Reservations> getReservationProperties(int id){
        return reservationRepo.findByPropertyId(id);
    }

//    public List<Reservations> getReservationsToBeCheckedOut(OffsetDateTime current_date) {
//        System.out.println(current_date);
//        return reservationRepo.findAllByEndDate(current_date);
//    }
//
//    public List<Reservations> getReservationsForLateCheckIn(OffsetDateTime current_date) {
////        System.out.println(current_date.toInstant()
////                .atOffset(ZoneOffset.UTC));
////        ;
////        return reservationRepo.findAllByStartDate(current_date.toInstant()
////                .atOffset(ZoneOffset.UTC));
//        java.sql.Timestamp ts = Timestamp.from( current_date.toInstant() );
//        System.out.println(ts);
//        return reservationRepo.findAllByStartTimeStamp(ts);
//    }
public List<Reservations> getReservationsToBeCheckedOut(){
        return reservationRepo.findAllByStatusEqualsAndCheckInDateIsNotNull("Payment Processed");
}

    public List<Reservations> getReservationsForLateCheckIn(){
        return reservationRepo.findAllByStatusEquals("Booked");
    }
    public List<Reservations> getHostReservations(int id) {
        Person p = personSpringDataRepo.findById(id).orElse(null);
        List<Property> ownerProperties = propertyService.getHostProperties(p);
        List<Reservations> ownerPropertyReservations = new ArrayList<>();
        List<Reservations> propertyIdReservations;
        for (Property property : ownerProperties) {
            propertyIdReservations = getReservationProperties(property.getPropertyId());
            for (Reservations r : propertyIdReservations) {
                ownerPropertyReservations.add(r);
            }
        }
        return ownerPropertyReservations;
    }

    public void createReservations(Reservations reservation) {
        int propertyId = reservation.getPropertyId();
        Property property = propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);
    }

    public int checkInReservation(Reservations reservation) throws ParseException {
        OffsetDateTime start_date = reservation.getStartDate();
        OffsetDateTime end_date = reservation.getEndDate();
        OffsetDateTime check_in_date = timeservice.getCurrentTime();
        LocalDate startDate = start_date.toLocalDate();
        LocalDate endDate = end_date.toLocalDate();
        LocalDate checkInDate = check_in_date.toLocalDate();
        System.out.println("start date " + start_date);
        System.out.println("check in date(current date time) " + check_in_date);
        int diff = checkInDate.compareTo(startDate);
        int checkInHour = check_in_date.getHour();
        System.out.println("Difference between start date and check in date " + diff);
        System.out.println("Check in hour is " + checkInHour);
        System.out.println("No of weekdays: " + getWeekdays(startDate, checkInDate));
        System.out.println("No of weekends: " + getWeekends(startDate, checkInDate));
        int diff1 = checkInDate.compareTo(endDate);
        if(reservation.getStatus().equals("Booked")) {
            if (diff < 0 || diff1 > 0 || (diff == 0 && checkInHour < 15)) {
                System.out.println("Early checkin not possible");
                return 0;
            }
            if ((diff == 0 && (checkInHour >= 15 && checkInHour < 24)) || (diff == 1 && checkInHour < 3)) {
                /* Proper check in, no extra charges */
                System.out.println("Proper checkin");
                reservation.setStatus("Payment Processed");
                reservation.setState("CheckedIn");
                float totalPrice = ((reservation.getBookedPriceWeekday() * getWeekdays(startDate, endDate)) + (reservation.getBookedPriceWeekend() * getWeekends(startDate, endDate)));
                reservation.setPaymentAmount(totalPrice);
            } else {
//            System.out.println("Check in after 3 am");
//            DayOfWeek day = check_in_date.getDayOfWeek();
//            float penalty = 0;
//            boolean nextDayPenalty = false;
//            LocalDate nextDay = startDate.plusDays(1);
//            System.out.println("Start Date " + reservation.getStartDate());
//            System.out.println("End Date " + reservation.getEndDate());
//            System.out.println("Next day of start date " + nextDay);
//            if (endDate.compareTo(nextDay) > 0) {
//                nextDayPenalty = true;
//            }
//
//            if (day.toString().equals("SATURDAY") || day.toString().equals("SUNDAY")) {
//                penalty = (float) -(0.3 * reservation.getBookedPriceWeekend());
//                if (nextDayPenalty) {
//                    penalty += (0.3 * reservation.getBookedPriceWeekend());
//                }
//            } else {
//                penalty = (float) -(0.3 * reservation.getBookedPriceWeekday());
//                if (nextDayPenalty) {
//                    penalty += (0.3 * reservation.getBookedPriceWeekday());
//                }
//            }
//            if (nextDayPenalty) {
//                if (nextDay.getDayOfWeek().equals("SATURDAY") || nextDay.getDayOfWeek().equals("SUNDAY")) {
//                    penalty += (0.3 * reservation.getBookedPriceWeekend());
//                } else {
//                    penalty += (0.3 * reservation.getBookedPriceWeekday());
//                }
//            }
//            System.out.println("penalty"+penalty);
//            reservation.setPenaltyValue(penalty);
//            reservation.setPenaltyReason("No Show");
//            reservation.setStatus("Available");
//            reservation.setState("NoShow");
//            reservation.setPaymentAmount(penalty);
                System.out.println("Improper checkin");
                return 0;
            }

            reservation.setCheckInDate(check_in_date.plusHours(8));
            reservationRepo.save(reservation);
            return 1;
        }else{
            System.out.println("Not a booked property");
            return 0;
        }

    }

    public int checkOutReservation(Reservations reservation) throws ParseException {
        if(reservation.getStatus().equals("Payment Processed")) {
            OffsetDateTime check_out_date = timeservice.getCurrentTime();
            System.out.println("offset utc date checkout " + check_out_date);
            LocalDate current_date = check_out_date.toLocalDate();
            LocalDate nextDate = current_date.plusDays(1);
            DayOfWeek checkOutDay = check_out_date.getDayOfWeek();
            String checkOutDayVal = checkOutDay.toString();
            DayOfWeek nextDay = nextDate.getDayOfWeek();
            String nextDayVal = nextDay.toString();
            LocalDate end_date = reservation.getEndDate().toLocalDate();
            System.out.println("offset utc date from time service " + current_date);
            System.out.println("offset utc date end date " + end_date);
            System.out.println("Difference between end date and checkout date " + current_date.compareTo(end_date));
            int diff = current_date.compareTo(end_date);
            int checkOutHour = check_out_date.getHour();
            float penalty = 0;
            System.out.println("Difference between end date and check out date " + diff);
            System.out.println("Check out hour is " + checkOutHour);
            if (diff == 0) {
                System.out.println("No difference between check out date and end date");
            } else if (diff < 0) {
                System.out.println("Check out date is earlier than end date");
                if (diff == -1) {
                    if (checkOutHour >= 15) {
                        System.out.println("Check out happened after 3 pm before one day so no change in charges");
                    } else {
                        System.out.println("Check out happened before 3 pm so 30% for current day and no charges for next day");
                        if (checkOutDayVal.equals("SATURDAY") || checkOutDayVal.equals("SUNDAY")) {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekend());
                        } else {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekday());
                        }
                        if (nextDayVal.equals("SATURDAY") || nextDayVal.equals("SUNDAY")) {
                            penalty += (float) (reservation.getBookedPriceWeekend());
                        } else {
                            penalty += (float) (reservation.getBookedPriceWeekday());
                        }
                    }
                } else if (diff <= -2) {
                    if (checkOutHour >= 15) {
                        System.out.println("Check out happened after 3 pm before 2 days so full charges for current day and 30% for next day");
                        if (nextDayVal.equals("SATURDAY") || nextDayVal.equals("SUNDAY")) {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekend());
                        } else {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekday());
                        }
                        penalty += ((reservation.getBookedPriceWeekday() * getWeekdays(nextDate.plusDays(1), end_date)) + (reservation.getBookedPriceWeekend() * getWeekends(nextDate.plusDays(1), end_date)));
                    } else {
                        System.out.println("Check out happened before 3 pm before 2 days so 30% for current day and no further charges from next day");
                        if (checkOutDayVal.equals("SATURDAY") || checkOutDayVal.equals("SUNDAY")) {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekend());
                        } else {
                            penalty = (float) (0.3 * reservation.getBookedPriceWeekday());
                        }
                        penalty += ((reservation.getBookedPriceWeekday() * getWeekdays(nextDate, end_date)) + (reservation.getBookedPriceWeekend() * getWeekends(nextDate, end_date)));
                    }
                }
                System.out.println(penalty);
                reservation.setPenaltyValue(penalty);
                reservation.setPenaltyReason("Early Checkout");
            } else {
                System.out.println("Checkout can't be greater than end date");
                return 0;
            }
            reservation.setStatus("Available");
            reservation.setState("CheckedOut");
            reservation.setCheckOutDate(check_out_date.plusHours(8));
            reservationRepo.save(reservation);
            return 1;
        }
        else{
            System.out.println("Property not checked in yet");
            return 0;
        }
    }

    public void cancelReservationByGuest(Reservations reservation) throws ParseException {
        OffsetDateTime cancellation_date = timeservice.getCurrentTime();
        System.out.println("offset utc date cancellation " + cancellation_date);
        LocalDate current_date = cancellation_date.toLocalDate();
        LocalDate startDate = reservation.getStartDate().toLocalDate();
        DayOfWeek startDay = startDate.getDayOfWeek();
        String startDayVal = startDay.toString();
        LocalDate nextDate = startDate.plusDays(1);
        DayOfWeek nextDay = nextDate.getDayOfWeek();
        String nextDayVal = nextDay.toString();
        System.out.println("offset utc date from time service " + current_date);
        System.out.println("offset utc date start date " + startDate);
        System.out.println("Difference between start date and cancellation date " + current_date.compareTo(startDate));
        int diff = current_date.compareTo(startDate);
        int cancellationHour = cancellation_date.getHour();
        float penalty = 0;
        System.out.println("Difference between start date and cancellation date " + diff);
        System.out.println("cancellation hour is " + cancellationHour);
        if (diff <= -2) {
            System.out.println("No penalty as 2 days ahead cancellation");
        } else if ((diff == -1 && cancellationHour >= 15) || (diff == 0 && cancellationHour < 15)) {
            System.out.println("Cancellation done one day prior so only 30% of start date");
            if (startDayVal.equals("SATURDAY") || startDayVal.equals("SUNDAY")) {
                penalty = (float) (0.3 * reservation.getBookedPriceWeekend());
            } else {
                penalty = (float) (0.3 * reservation.getBookedPriceWeekday());
            }
        } else if ((diff == 0 && cancellationHour >= 15) || (diff == 1 && cancellationHour < 3)) {
            System.out.println("cancellation happened before 3 pm before one day so 30% of first day");
            if (startDayVal.equals("SATURDAY") || startDayVal.equals("SUNDAY")) {
                penalty = (float) (0.3 * reservation.getBookedPriceWeekend());
            } else {
                penalty = (float) (0.3 * reservation.getBookedPriceWeekday());
            }
            System.out.println("check if user has next day booking");
            if (nextDate.compareTo(reservation.getEndDate().toLocalDate()) != 0) {
                if (nextDayVal.equals("SATURDAY") || nextDayVal.equals("SUNDAY")) {
                    penalty += (float) (reservation.getBookedPriceWeekend());
                } else {
                    penalty += (float) (reservation.getBookedPriceWeekday());
                }
            }
        }
        System.out.println(penalty);
        reservation.setPenaltyValue(penalty);
        reservation.setPenaltyReason("Cancelled by Guest");
        reservation.setStatus("Available");
        reservation.setState("CancelledByGuest");
        reservationRepo.save(reservation);

    }

    public void cancelReservationByHost(Reservations reservation) throws ParseException {
        OffsetDateTime cancellation_date = timeservice.getCurrentTime();
        System.out.println("offset utc date cancellation " + cancellation_date);
        LocalDate current_date = cancellation_date.toLocalDate();
        LocalDate startDate = reservation.getStartDate().toLocalDate();
        LocalDate endDate = reservation.getEndDate().toLocalDate();
        DayOfWeek startDay = startDate.getDayOfWeek();
        String startDayVal = startDay.toString();
        LocalDate nextDate = startDate.plusDays(1);
        DayOfWeek nextDay = nextDate.getDayOfWeek();
        String nextDayVal = nextDay.toString();
        System.out.println("offset utc date from time service " + current_date);
        System.out.println("offset utc date start date " + startDate);
        System.out.println("Difference between start date and cancellation date " + current_date.compareTo(startDate));
        System.out.println("Difference between cancellation date and end date " + endDate.compareTo(current_date));
        int diff = current_date.compareTo(startDate);
        int diffend = endDate.compareTo(current_date);
        int cancellationHour = cancellation_date.getHour();
        float penalty = 0;
        System.out.println("Difference between start date and cancellation date " + diff);
        System.out.println("cancellation hour is " + cancellationHour);
        System.out.println("cancellation hour is " + startDayVal);

        //need to get parking fee
        Property p = propertyService.getProperty(reservation.getPropertyId());


// checkout hua check karna padega
// checkout hua check karna padega
// checkout date comparision
        float remainingDaysPrice = 0;
        if(reservation.getStatus().equals("Booked")) {

            if (diff >= 7) {
                System.out.println("No penalty as there is a 7 days in start date");
            } else if (diff > 0 && reservation.getCheckInDate() == null) {
                //apply 15% changes to the host as penalty  (total fee for that day, including parking.)
                if (startDayVal.equals("SATURDAY") || startDayVal.equals("SUNDAY")) {
                    penalty = (float) (0.15 * (reservation.getBookedPriceWeekend() + p.getParkingFee()));
                } else {
                    penalty = (float) (0.15 * (reservation.getBookedPriceWeekday() + p.getParkingFee()));
                }
            } else if ((diff <= 0 && cancellationHour <= 15) && reservation.getCheckInDate() != null) {
                System.out.println("Cancellation done one day prior so only 30% of start date");

                //User will get back below price
                int remainingWeekDays = getWeekdays(current_date, endDate);
                int remainingWeekEnds = getWeekdays(current_date, endDate);
                remainingDaysPrice = (remainingWeekDays * reservation.getBookedPriceWeekday()) +
                        (remainingWeekEnds* reservation.getBookedPriceWeekend());


                penalty = penalty + (float) (0.15 * remainingWeekEnds * reservation.getBookedPriceWeekend());
                penalty = penalty + (float) (0.15 * remainingWeekDays * reservation.getBookedPriceWeekday());


            } else if ((diff <= 0 && cancellationHour > 15) && reservation.getCheckInDate() != null) {

                current_date = current_date.plusDays(1);

                //User will get back below price
                int remainingWeekDays = getWeekdays(current_date, endDate);
                int remainingWeekEnds = getWeekdays(current_date, endDate);
                remainingDaysPrice = (remainingWeekDays * reservation.getBookedPriceWeekday()) +
                        (remainingWeekEnds* reservation.getBookedPriceWeekend());

                penalty = penalty + (float) (0.15 * remainingWeekEnds * reservation.getBookedPriceWeekend());
                penalty = penalty + (float) (0.15 * remainingWeekDays * reservation.getBookedPriceWeekday());
            }

            System.out.println(penalty);
            float bookp = reservation.getBookedPrice();
            reservation.setBookedPrice(bookp - remainingDaysPrice);
            reservation.setPenaltyValue(penalty);
            reservation.setPenaltyReason("Cancelled by Host");
            reservation.setStatus("Available");
            reservation.setState("CancelledByHost");
            reservationRepo.save(reservation);
        }

    }

    public int getWeekdays(LocalDate startDate, LocalDate endDate) {
        int weekdays = 0;
        for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date = date.plusDays(1)) {
            System.out.println("Traversing date " + date);
            System.out.println("Day is " + date.getDayOfWeek());
            if (!date.getDayOfWeek().toString().equals("SATURDAY") && !date.getDayOfWeek().toString().equals("SUNDAY")) {
                weekdays += 1;
            }
        }
        return weekdays;
    }

    public int getWeekends(LocalDate startDate, LocalDate endDate) {
        int weekend = 0;
        for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date = date.plusDays(1)) {
            System.out.println("Traversing date " + date);
            System.out.println("Day is " + date.getDayOfWeek());
            if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")) {
                weekend += 1;
            }
        }
        return weekend;
    }
    public void removeReservation(int id) {
        reservationRepo.deleteById(id);
    }
}
