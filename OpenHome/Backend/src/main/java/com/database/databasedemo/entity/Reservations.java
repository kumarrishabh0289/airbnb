package com.database.databasedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="booked_price_weekend")
    private float bookedPriceWeekend;

    @Column(name="booked_price_weekday")
    private float bookedPriceWeekday;

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column(name="payment_amount")
    private float paymentAmount;

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
    private OffsetDateTime bookingDate;

    public void setBookingDate(OffsetDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Column(name="start_date")
    private OffsetDateTime startDate;

    @Column(name="end_date")
    private OffsetDateTime endDate;

    @Column(name="check_in_date")
    private OffsetDateTime checkInDate;

    @Column(name="check_out_date")
    private OffsetDateTime checkOutDate;

    @Column(name="guest_id")
    private int guestId;

    @Column(name="penalty_value")
    private float penaltyValue;

    public float getPenaltyValue() {
        return penaltyValue;
    }

    public void setPenaltyValue(float penaltyValue) {
        this.penaltyValue = penaltyValue;
    }

    public String getPenaltyReason() {
        return penaltyReason;
    }

    public void setPenaltyReason(String penaltyReason) {
        this.penaltyReason = penaltyReason;
    }

    @Column(name="penalty_reason")
    private String penaltyReason;

    @Column(name="status")
    private String status;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="state")
    private String state;

    public Reservations(){

    }
    public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId) {
        this.bookedPrice = bookedPrice;
        this.bookedPriceWeekend = bookedPriceWeekend;
        this.bookedPriceWeekday = bookedPriceWeekday;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.propertyId = propertyId;
    }

    public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId, String address, String description, String status) {
        this.bookedPrice = bookedPrice;
        this.bookedPriceWeekend = bookedPriceWeekend;
        this.bookedPriceWeekday = bookedPriceWeekday;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.propertyId = propertyId;
        this.address = address;
        this.description = description;
        this.status = status;
    }

    public Reservations(float bookedPrice, float bookedPriceWeekend, float bookedPriceWeekday, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, int guestId, int propertyId, String address, String description) {
        this.bookedPrice = bookedPrice;
        this.bookedPriceWeekend = bookedPriceWeekend;
        this.bookedPriceWeekday = bookedPriceWeekday;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.propertyId = propertyId;
        this.address = address;
        this.description = description;
    }


    public Reservations(float bookedPrice, String description, String address, float bookedPriceWeekend, float bookedPriceWeekday, float paymentAmount, OffsetDateTime bookingDate, OffsetDateTime startDate, OffsetDateTime endDate, OffsetDateTime checkInDate, OffsetDateTime checkOutDate, int guestId, float penaltyValue, String penaltyReason, String status, int propertyId) {
        this.bookedPrice = bookedPrice;
        this.description = description;
        this.address = address;
        this.bookedPriceWeekend = bookedPriceWeekend;
        this.bookedPriceWeekday = bookedPriceWeekday;
        this.paymentAmount = paymentAmount;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestId = guestId;
        this.penaltyValue = penaltyValue;
        this.penaltyReason = penaltyReason;
        this.status = status;
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public OffsetDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(OffsetDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public OffsetDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(OffsetDateTime checkOutDate) {
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

    public OffsetDateTime getBookingDate() {
        return bookingDate;
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
                ", Address=" + address +
                ", description=" + description +
                '}';
    }
}
