package com.database.databasedemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int roomId;

//    @Column(name = "room_description", nullable = false, length = 100)
//    private String roomDescription;

    @Column(name = "weekend_price", nullable = false)
    private int weekendPrice;

    @Column(name="room_square_footage")
    private int roomSquareFootage;

    @Column(name="bath_included")
    private boolean bathIncluded;

    @Column(name="shower_included")
    private boolean showerIncluded;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(int weekendPrice) {
        this.weekendPrice = weekendPrice;
    }


    public int getRoomsquareFootage() {
        return roomSquareFootage;
    }

    public void setRoomSquareFootage(int roomSquareFootage) {
        this.roomSquareFootage = roomSquareFootage;
    }

    public boolean isShowerIncluded() {
        return showerIncluded;
    }

    public void setShowerIncluded(boolean showerIncluded) {
        this.showerIncluded = showerIncluded;
    }

    public boolean isBathIncluded() {
        return bathIncluded;
    }

    public void setBathIncluded(boolean bathIncluded) {
        this.bathIncluded = bathIncluded;
    }



    public float getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(float weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }
/*
    public List<Reservations> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservations> reservationList) {
        this.reservationList = reservationList;
    }

 */

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Column(name = "weekday_price", nullable = false)
    private float weekdayPrice;


   // @OneToMany(mappedBy="room", targetEntity = Reservations.class, fetch = FetchType.EAGER)
   // private List<Reservations> reservationList;

    @JoinColumn(name="property_id",nullable=false,insertable = true, updatable = true)
    @ManyToOne(optional=false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Property property;

    public Room(int weekendPrice, int roomSquareFootage, boolean bathIncluded, boolean showerIncluded, float weekdayPrice, Property property) {
        this.weekendPrice = weekendPrice;
        this.roomSquareFootage = roomSquareFootage;
        this.bathIncluded = bathIncluded;
        this.showerIncluded = showerIncluded;
        this.weekdayPrice = weekdayPrice;
        this.property = property;
    }

    public Room() {
        super();
    }


}
