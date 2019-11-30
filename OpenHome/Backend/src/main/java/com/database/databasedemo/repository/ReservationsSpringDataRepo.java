package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Reservations;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationsSpringDataRepo extends JpaRepository<Reservations, Integer> {

    //@Query("SELECT * FROM reservations r WHERE r.guest_id = ?1")
    //List<Reservations> findByGuest_id(int guest_id);
}

