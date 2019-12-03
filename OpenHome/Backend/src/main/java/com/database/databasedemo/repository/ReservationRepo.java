package com.database.databasedemo.repository;


import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Qualifier("reservations")
@Repository
public interface ReservationRepo extends JpaRepository<Reservations,Integer>, JpaSpecificationExecutor {

  //  List<Reservations> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(OffsetDateTime start_date, OffsetDateTime end_date);



    //@Query(value = "from Reservations t where yourDate BETWEEN :start_date AND :end_date")
//    @Query("SELECT Reservations FROM Reservations WHERE end_date >= ?1 AND   start_date <= ?2 ")
//    List<Reservations> getAllBetweenDates(OffsetDateTime start_date, OffsetDateTime end_date);


    @Query("SELECT * FROM reservations r WHERE r.guest_id = ?1")
    List<Reservations> findByGuestId(int guestId);

    @Query("SELECT * FROM reservations r WHERE r.property_id = ?1")
    List<Reservations> findByPropertyId(int propertyId);


//    @Query("SELECT p FROM person p WHERE p.name = ?1")
//    Person findByName(String name);


}

