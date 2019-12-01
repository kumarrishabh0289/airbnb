package com.database.databasedemo.repository;


import com.database.databasedemo.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.List;

public interface ReservationRepo  extends JpaRepository<Reservations,Integer> {

    @Query("SELECT * FROM reservations a WHERE a.guest_id = ?1")
    List<Reservations> findByGuestId(int guestId);


}

