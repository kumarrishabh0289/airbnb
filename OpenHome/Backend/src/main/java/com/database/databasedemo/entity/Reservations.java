package com.database.databasedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.OffsetDateTime;
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
    private OffsetDateTime startDate;

    @Column(name="end_date")
    private OffsetDateTime endDate;

    @Column(name="check_in_date")
    private Date checkInDate;

    @Column(name="check_out_date")
    private Date checkOutDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBookedPrice() {
        return bookedPrice;
    }

    public void setBookedPrice(float bookedPrice) {
        this.bookedPrice = bookedPrice;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Person getGuest() {
        return guest;
    }

    public void setGuest(Person guest) {
        this.guest = guest;
    }

//    public Property getProperty() {
//        return property;
//    }
//

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
//    public void setProperty(Property property) {
//        this.property = property;
//    }

    @ManyToOne
    private Person guest;

//    @JoinColumn(name="property_id",nullable=false,insertable = true, updatable = true)
//    @ManyToOne(optional=false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Property property;
    @JoinColumn(name="property_id",nullable=false,insertable = true, updatable = true)
    int propertyId;
}
