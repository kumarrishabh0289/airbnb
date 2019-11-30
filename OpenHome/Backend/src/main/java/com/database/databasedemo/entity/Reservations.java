package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservations")
public class Reservations {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private float booked_price;

    private float booked_price_weekend;
    private float booked_price_weekday;
    private float parking_price;
    private Date start_date;
    private Date end_date;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Person guest;
    private Date booking_date;

    private Date check_in_date;

    private Date check_out_date;

    public Reservations(float booked_price, float booked_price_weekend, float booked_price_weekday, float parking_price, Date start_date, Date end_date, Person guest, Date booking_date, Date check_in_date, Date check_out_date, int room_id) {
        this.booked_price = booked_price;
        this.booked_price_weekend = booked_price_weekend;
        this.booked_price_weekday = booked_price_weekday;
        this.parking_price = parking_price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.guest = guest;
        this.booking_date = booking_date;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.room_id = room_id;
    }

    private int room_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBooked_price() {
        return booked_price;
    }

    public void setBooked_price(float booked_price) {
        this.booked_price = booked_price;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }



    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", booked_price=" + booked_price +
                ", booked_price_weekend=" + booked_price_weekend +
                ", booked_price_weekday=" + booked_price_weekday +
                ", parking_price=" + parking_price +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", guest=" + guest +
                ", booking_date=" + booking_date +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", room_id=" + room_id +
                '}';
    }

    public float getBooked_price_weekend() {
        return booked_price_weekend;
    }

    public void setBooked_price_weekend(float booked_price_weekend) {
        this.booked_price_weekend = booked_price_weekend;
    }

    public float getBooked_price_weekday() {
        return booked_price_weekday;
    }

    public void setBooked_price_weekday(float booked_price_weekday) {
        this.booked_price_weekday = booked_price_weekday;
    }

    public float getParking_price() {
        return parking_price;
    }

    public void setParking_price(float parking_price) {
        this.parking_price = parking_price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Person getGuest() {
        return guest;
    }

    public void setGuest(Person guest) {
        this.guest = guest;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public Reservations(float booked_price, Date booking_date, Date start_date, Date end_date, int room_id) {
        this.booked_price = booked_price;
        this.booking_date = booking_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.room_id = room_id;
    }

    public Reservations()
    {

    }

}
