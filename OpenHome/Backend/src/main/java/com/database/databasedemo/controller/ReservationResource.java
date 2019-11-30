package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Room;
import com.database.databasedemo.repository.ReservationsSpringDataRepo;
import com.database.databasedemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@CrossOrigin(origins="*")
@RestController
public class ReservationResource {
    @Autowired
    private ReservationsSpringDataRepo ReservationRepo;

    @Autowired
    PropertyService propertyService;

    @PostMapping("/bookreservation")
    public ResponseEntity<Object> createReservation(@RequestBody Reservations reservation) {
        Reservations savedReservation = ReservationRepo.save(reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedReservation.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


    @PostMapping("/reservationconfirmation")
    public String reservationconfirmation(@RequestBody Map<String, String> payload) {
        String PropertyId = payload.get(payload.keySet().toArray()[0]);
        String StartDate = payload.get(payload.keySet().toArray()[1]);
        String EndDate = payload.get(payload.keySet().toArray()[2]);
        String RequiredRooms = payload.get(payload.keySet().toArray()[3]);
        String UserId = payload.get(payload.keySet().toArray()[4]);
        String Price = payload.get(payload.keySet().toArray()[5]);


        int Id = Integer.parseInt(PropertyId);
        System.out.println("Id"+Id);


        Property p = propertyService.getProperty(1);
        List<Room> RoomID = p.getRoomList();
        System.out.println(RoomID);


//        for(int i = 0; i <Integer.parseInt(RequiredRooms);i++){
//            ReservationRepo.save(Price, new java.util.Date(),StartDate,EndDate,RoomID[i].getRoomId());
//        }

        return "Very Good Reservation had been done by you";

    }
}
