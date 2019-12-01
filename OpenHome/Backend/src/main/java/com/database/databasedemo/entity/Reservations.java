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

    @Column(name="booked_price_weekend")
    private float bookedPriceWeekend;

    @Column(name="booked_price_weekday")
    private float bookedPriceWeekday;

    public float getBookedPriceWeekend() {
        return bookedPriceWeekend;
    }

    public void setBookedPriceWeekend(float bookedPriceWeekend) {
        this.bookedPriceWeekend = bookedPriceWeekend;
    }

    public float getBookedPriceWeekday() {
        return bookedPriceWeekday;
    }

    public void setBookedPriceWeekday(float bookedPriceWeekday) {
        this.bookedPriceWeekday = bookedPriceWeekday;
    }

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

    @Column(name="guest_id")
    private int guestId;

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

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


    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }


    @JoinColumn(name="property_id",nullable=false,insertable = true, updatable = true)
    int propertyId;

    public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, Date bookingDate, Date startDate, Date endDate, int guestId, int propertyId) {
        this.bookedPrice = bookedPrice;
        this.bookedPriceWeekend = bookedPriceWeekend;
        this.bookedPriceWeekday = bookedPriceWeekday;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.propertyId = propertyId;
    }
    public Reservations(){

    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", bookedPrice=" + bookedPrice +
                ", bookedPriceWeekend=" + bookedPriceWeekend +
                ", bookedPriceWeekday=" + bookedPriceWeekday +
                ", bookingDate=" + bookingDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", guestId=" + guestId +
                ", propertyId=" + propertyId +
                '}';
    }
}
