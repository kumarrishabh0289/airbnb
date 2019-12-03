package com.database.databasedemo.service;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    PropertyService propertyService;
    @Autowired
    TimeService timeService;

    public Reservations getReservation(int id) {
        return reservationRepo.findById(id).orElse(null);
    }

    public List<Reservations> getAllReservations(){
        return reservationRepo.findAll();
    }

    public List<Reservations> getGuestReservations(int id){
        return reservationRepo.findByGuestId(id);
    }

//    public List<Reservations> getHostReservations(int guestId){
//
//        return reservationRepo.findByGuestId(guestId);
//    }

    public void createReservations(Reservations reservation){
        int propertyId=reservation.getPropertyId();
        Property property=propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);
    }

    public void checkInReservation(Reservations reservation,String checkInDate) throws ParseException {
        System.out.println("Check In Date: "+checkInDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date1 = sdf1.parse(checkInDate);
        System.out.println("parsed date"+date1);
        OffsetDateTime check_in_date = date1.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("offset utc date "+check_in_date);
        OffsetDateTime start_date=reservation.getStartDate();
        OffsetDateTime end_date=reservation.getEndDate();

        OffsetDateTime current_time =timeService.getCurrentTime();
        System.out.println("current time"+current_time);
        long result
                = start_date.until(current_time,
                ChronoUnit.HOURS);
        // print results
        System.out.println("Result in hours: "
                + result);
//        System.out.println("No of weekdays: "+getWeekdays(start_date,check_in_date));
//        System.out.println("No of weekends: "+getWeekends(start_date,check_in_date));
        if (result>=15 && result<=27)
        {
            /* Proper check in, no extra charges */
            System.out.println("Proper checkin");
            reservation.setStatus("Payment Processed");
            float totalPrice=reservation.getBookedPriceWeekday()*getWeekdays(start_date,end_date)+reservation.getBookedPriceWeekend()*getWeekends(start_date,end_date);
            reservation.setPaymentAmount(totalPrice);
        }
        else if (result>27)
        {
        System.out.println("Check in after 3 am");
            DayOfWeek day=check_in_date.getDayOfWeek();
            float penalty=0;
            boolean nextDayPenalty=false;
            OffsetDateTime nextDay=reservation.getStartDate().plusHours(24);
            System.out.println("Start Date "+reservation.getStartDate());
            System.out.println("End Date "+reservation.getEndDate());
            System.out.println("Next day of start date "+nextDay);
            if(reservation.getEndDate().compareTo(nextDay)>0){
                nextDayPenalty=true;
            }

            if(day.toString().equals("SATURDAY")||day.toString().equals("SUNDAY")){
                penalty=(float)(reservation.getBookedPriceWeekend()-(0.3*reservation.getBookedPriceWeekend()));
                if(nextDayPenalty){
                    penalty-=(0.3*reservation.getBookedPriceWeekend());
                }
            }
            else{
                penalty=(float)(reservation.getBookedPriceWeekday()-(0.3*reservation.getBookedPriceWeekday()));
                if(nextDayPenalty){
                    penalty-=(0.3*reservation.getBookedPriceWeekday());
                }
            }
            if(nextDayPenalty){
                if(nextDay.getDayOfWeek().equals("SATURDAY")||nextDay.getDayOfWeek().equals("SUNDAY")){
                    penalty-=(0.3*reservation.getBookedPriceWeekend());
                }
                else{
                    penalty-=(0.3*reservation.getBookedPriceWeekday());
                }
            }
            System.out.println(penalty);
            reservation.setPenaltyValue(penalty);
            reservation.setPenaltyReason("Late Check-In");
            reservation.setStatus("Cancelled");
            reservation.setPaymentAmount(penalty);
        }

        //reservation.setCheckInDate(check_in_date);
        reservation.setCheckInDate(current_time);
        reservationRepo.save(reservation);

    }
    public void checkOutReservation(Reservations reservation,String checkOutDate) throws ParseException {
        System.out.println("Check Out Date: "+checkOutDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date1 = sdf1.parse(checkOutDate);
        System.out.println("parsed date"+date1);
        OffsetDateTime check_out_date = date1.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("offset utc date "+check_out_date);
        OffsetDateTime start_date=reservation.getStartDate();
        OffsetDateTime end_date=reservation.getEndDate();
        System.out.println("Difference between dates "+reservation.getEndDate().compareTo(check_out_date));
        if(check_out_date.compareTo(end_date)>1){
            System.out.println(end_date.compareTo(check_out_date));
        }
        reservation.setStatus("Available");
        reservation.setCheckOutDate(check_out_date);
        reservationRepo.save(reservation);

    }

    public int getWeekdays(OffsetDateTime startDate,OffsetDateTime endDate){
        int weekdays=0;
        for(OffsetDateTime date=startDate;date.compareTo(endDate)<=0;date=date.plusHours(24)){
            System.out.println("Traversing date "+date);
            System.out.println("Day is "+date.getDayOfWeek());
            if(!date.getDayOfWeek().toString().equals("SATURDAY") && !date.getDayOfWeek().toString().equals("SUNDAY")){
                weekdays+=1;
            }
        }
        return weekdays;
    }

    public int getWeekends(OffsetDateTime startDate,OffsetDateTime endDate){
        int weekend=0;
        for(OffsetDateTime date=startDate;date.compareTo(endDate)<=0;date=date.plusHours(24)){
            System.out.println("Traversing date "+date);
            System.out.println("Day is "+date.getDayOfWeek());
            if(date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")){
                weekend+=1;
            }
        }
        return weekend;
    }


    public void removeReservation(int id){
        reservationRepo.deleteById(id);
    }
}
