package com.database.databasedemo.repository;


import com.database.databasedemo.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReservationRepo  extends JpaRepository<Reservations,Integer>, JpaSpecificationExecutor {
}
