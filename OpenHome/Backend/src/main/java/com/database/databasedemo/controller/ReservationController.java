package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.ReservationRepo;
import com.database.databasedemo.service.PropertyService;
import com.database.databasedemo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ReservationController {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    PropertyService propertyService;

    @Autowired
    ReservationService reservationService;
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
    public ResponseEntity<?> newReservation(@RequestBody Map<String, String> payload) throws ParseException {
        String bookingDate = payload.get(payload.keySet().toArray()[0]);

        System.out.println(bookingDate);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf1.parse(bookingDate);
        OffsetDateTime offsetDateTime = date1.toInstant()
                .atOffset(ZoneOffset.UTC);

        String guestId = payload.get(payload.keySet().toArray()[1]);

        System.out.println(guestId);
        int guest_id=Integer.parseInt(guestId);

        String propertyId = payload.get(payload.keySet().toArray()[2]);

        System.out.println(propertyId);

        int id=Integer.parseInt(propertyId);
        Property property=propertyService.getProperty(id);
        Reservations reservation=new Reservations(offsetDateTime,guest_id,id);
        property.addReservation(reservation);
        reservationRepo.save(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/reservation/{id}")
    public Reservations getReservation(@PathVariable int id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/reservation/guest/{guestId}")
    public List<Reservations> getGuestReservation(@PathVariable String guestId) {
        System.out.println(guestId);
        int id= Integer.parseInt(guestId);
        return reservationService.getHostReservations(id);
    }


}
