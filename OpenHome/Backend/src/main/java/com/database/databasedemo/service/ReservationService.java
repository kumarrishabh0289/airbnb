package com.database.databasedemo.service;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    PropertyService propertyService;

    public Reservations getReservation(int id) {
        return reservationRepo.findById(id).orElse(null);
    }

    public List<Reservations> getAllReservations(){
        return reservationRepo.findAll();
    }

    public List<Reservations> getHostReservations(int guestId){

        return reservationRepo.findByGuestId(guestId);
    }

    public void createReservations(Reservations reservation){
        int propertyId=reservation.getPropertyId();
        Property property=propertyService.getProperty(propertyId);
        property.addReservation(reservation);
        reservationRepo.save(reservation);
    }

    public void removeReservation(int id){
        reservationRepo.deleteById(id);
    }
}
