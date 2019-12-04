package com.database.databasedemo.repository;

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

    @Query("SELECT * FROM reservations r WHERE r.guest_id = ?1")
    List<Reservations> findByGuestId(int guestId);

    @Query("SELECT * FROM reservations r WHERE r.property_id = ?1")
    List<Reservations> findByPropertyId(int propertyId);

//    @Query("SELECT * FROM reservations r WHERE r.end_date <= ?1 AND r.status='Payment Processed'")
//    List<Reservations> findAllByEndDate(OffsetDateTime current_date);
//
//    @Query("SELECT * FROM reservations r WHERE r.start_date <= ?1 AND r.status='Booked'")
//    List<Reservations> findAllByStartDate(OffsetDateTime current_date);

    List<Reservations> findAllByStatusEqualsAndCheckInDateIsNotNull(String status);

    List<Reservations> findAllByStatusEquals(String status);



//    @Query("SELECT p FROM person p WHERE p.name = ?1")
//    Person findByName(String name);


}

