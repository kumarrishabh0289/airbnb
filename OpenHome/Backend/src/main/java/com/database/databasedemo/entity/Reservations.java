package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservations")
public class Reservations {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="booked_price")
    private float bookedPrice;

    @Column(name="booking_date")
    private Date bookingDate;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="check_in_date")
    private Date checkInDate;

    @Column(name="check_out_date")
    private Date checkOutDate;

    @ManyToOne
    private Person guest;

    @ManyToOne
    private Room room;
}
