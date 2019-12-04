package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.ReservationRepo;
import com.database.databasedemo.service.PropertyService;
import com.database.databasedemo.service.ReservationService;
import com.database.databasedemo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    PropertyService propertyService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    TimeService timeService;

    @PostMapping("/reservation/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createReservation(@RequestBody Reservations reservation) {
        int propertyId=reservation.getPropertyId();
        Property property=propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/reservation/new")
    @ResponseStatus(value = HttpStatus.CREATED)
    //public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId) {
    public ResponseEntity<?> newReservation(@RequestBody Map<String, String> payload) throws ParseException {

        System.out.println("payload"+payload);
        String bookedPrice = payload.get(payload.keySet().toArray()[0]);
        int booked_price=Integer.parseInt(bookedPrice);

        String bookedPriceWeekend = payload.get(payload.keySet().toArray()[1]);
        int booked_price_weekend=Integer.parseInt(bookedPriceWeekend);

        String bookedPriceWeekday = payload.get(payload.keySet().toArray()[2]);
        int booked_price_weekday=Integer.parseInt(bookedPriceWeekday);


        String bookingDate = payload.get(payload.keySet().toArray()[3]);

        System.out.println(bookingDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf1.parse(bookingDate);
        OffsetDateTime booking_date = date1.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("booking_date"+booking_date);
        String startDate = payload.get(payload.keySet().toArray()[4]);
        System.out.println("startDate"+startDate);
        System.out.println(startDate);
        java.util.Date date2 = sdf1.parse(startDate);
        OffsetDateTime start_date = date2.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("start_date "+start_date);
        String endDate = payload.get(payload.keySet().toArray()[5]);
        System.out.println("endDate "+endDate );
        System.out.println(endDate);
        java.util.Date date3 = sdf1.parse(endDate);
        OffsetDateTime end_date = date3.toInstant()
                .atOffset(ZoneOffset.UTC);

        System.out.println("end_date "+end_date );
        String guestId = payload.get(payload.keySet().toArray()[6]);

        System.out.println(guestId);
        int guest_id=Integer.parseInt(guestId);

        String propertyId = payload.get(payload.keySet().toArray()[7]);

        System.out.println(propertyId);

        int id=Integer.parseInt(propertyId);
        Property property=propertyService.getProperty(id);

        String address = payload.get(payload.keySet().toArray()[8]);
        String description = payload.get(payload.keySet().toArray()[9]);

        Reservations reservation=new Reservations(booked_price,booked_price_weekend,booked_price_weekday,booking_date, start_date, end_date,guest_id,id,address,description);
        reservation.setStatus("Booked");
        reservation.setState("Booked");
        property.addReservation(reservation);
        reservationRepo.save(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public Reservations getReservation(@PathVariable int id) {
        return reservationService.getReservation(id);
    }

//    @GetMapping("/reservation/guest/{guestId}")
//    public List<Reservations> getGuestReservation(@PathVariable String guestId) {
//        System.out.println(guestId);
//        int id= Integer.parseInt(guestId);
//        return reservationService.getHostReservations(id);
//    }
@GetMapping("/reservation/guest/{id}")
public List<Reservations> getGuestReservations(@PathVariable int id) {
    return reservationService.getGuestReservations(id);
}

    @GetMapping("/reservation/host/{id}")
    public List<Reservations> getHostReservations(@PathVariable int id) {
        return reservationService.getHostReservations(id);
    }

    @PostMapping("/reservation/checkin")
    @ResponseStatus(value = HttpStatus.CREATED)
    //public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId) {
    public ResponseEntity<?> checkinReservation(@RequestBody Map<String, String> payload) throws ParseException {

        String reservationId = payload.get(payload.keySet().toArray()[0]);
        int reservation_id = Integer.parseInt(reservationId);
        Optional<Reservations> r = reservationRepo.findById(reservation_id);
        if (!r.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //String checkInDate = payload.get(payload.keySet().toArray()[1]);
        Reservations reservation = reservationService.getReservation(reservation_id);
        int result = reservationService.checkInReservation(reservation);
        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/reservation/checkout")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> checkoutReservation(@RequestBody Map<String, String> payload) throws ParseException {
        String reservationId = payload.get(payload.keySet().toArray()[0]);
        int reservation_id = Integer.parseInt(reservationId);
        //String checkOutDate = payload.get(payload.keySet().toArray()[1]);

        Optional<Reservations> r = reservationRepo.findById(reservation_id);
        if (!r.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        Reservations reservation = reservationService.getReservation(reservation_id);
        reservationService.checkOutReservation(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reservation/guest/cancel")
    @ResponseStatus(value = HttpStatus.CREATED)

    public ResponseEntity<?> cancelReservationByGuest(@RequestBody Map<String, String> payload) throws ParseException {
        String reservationId = payload.get(payload.keySet().toArray()[0]);
        int reservation_id = Integer.parseInt(reservationId);
        //String checkOutDate = payload.get(payload.keySet().toArray()[1]);
        Reservations reservation = reservationService.getReservation(reservation_id);
        reservationService.cancelReservationByGuest(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reservation/host/cancel")
    @ResponseStatus(value = HttpStatus.CREATED)

    public ResponseEntity<?> cancelReservationByHost(@RequestBody Map<String, String> payload) throws ParseException {
        String reservationId = payload.get(payload.keySet().toArray()[0]);
        int reservation_id = Integer.parseInt(reservationId);
        //String checkOutDate = payload.get(payload.keySet().toArray()[1]);
        Reservations reservation = reservationService.getReservation(reservation_id);
        reservationService.cancelReservationByHost(reservation);
        Reservations reservation = reservationService.getReservation(reservation_id);
        reservationService.cancelReservationByGuest(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/reservation/autocheckout")
    @ResponseStatus(value = HttpStatus.CREATED)

    public List<Reservations> autocheckoutReservation() throws ParseException {

        return reservationService.getReservationsToBeCheckedOut();
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/reservation/noshow")
    @ResponseStatus(value = HttpStatus.CREATED)

    public List<Reservations> noshowReservation() throws ParseException {

        return reservationService.getReservationsForLateCheckIn();
        //return new ResponseEntity<>(HttpStatus.OK);
    }
}
