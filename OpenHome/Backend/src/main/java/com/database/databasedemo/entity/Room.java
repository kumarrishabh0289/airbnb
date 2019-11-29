package com.database.databasedemo.entity;
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

    @Column(name="square_footage")
    private int squareFootage;

    @Column(name="bath_included")
    private boolean bathIncluded;

    @Column(name="shower_included")
    private boolean shower_included;

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

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    public boolean isBathIncluded() {
        return bathIncluded;
    }

    public void setBathIncluded(boolean bathIncluded) {
        this.bathIncluded = bathIncluded;
    }

    public boolean isShower_included() {
        return shower_included;
    }

    public void setShower_included(boolean shower_included) {
        this.shower_included = shower_included;
    }

    public float getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(float weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    public List<Reservations> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservations> reservationList) {
        this.reservationList = reservationList;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Column(name = "weekday_price", nullable = false)
    private float weekdayPrice;


    @OneToMany(mappedBy="room", targetEntity = Reservations.class, fetch = FetchType.EAGER)
    private List<Reservations> reservationList;

    @JoinColumn(name="property_id",nullable=false,insertable = true, updatable = true)
    @ManyToOne(optional=false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Property property;


    public Room() {
        super();
    }


}
