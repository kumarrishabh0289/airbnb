package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false)
    private int propertyId;

    @Column(name = "property_description", nullable = false, length = 100)
    private String propertyDescription;

    public Property(String propertyDescription, String streetName, String city, String state, int zipcode, String picture,String propertyType, String sharingType, int numberOfRooms, int totalSquareFootage,Person owner, int weekdayPrice,int weekendPrice, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun, String wifi, String status) {
        this.propertyDescription = propertyDescription;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.propertyType = propertyType;
        this.sharingType = sharingType;
        this.numberOfRooms = numberOfRooms;
        this.totalSquareFootage = totalSquareFootage;
        this.owner = owner;
        this.picture = picture;
        this.weekendPrice=weekendPrice;
        this.weekdayPrice=weekdayPrice;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.wifi = wifi;
        this.status = status;
    }

    public Property(String propertyDescription, String streetName, String city, String state, int zipcode, String propertyType, String sharingType, int numberOfRooms, int totalSquareFootage, boolean parking, float parkingFee, String wifi, boolean laundry, String view, boolean smoking, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun, String picture, Person owner) {
        this.propertyDescription = propertyDescription;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.propertyType = propertyType;
        this.sharingType = sharingType;
        this.numberOfRooms = numberOfRooms;
        this.totalSquareFootage = totalSquareFootage;
        this.parking = parking;
        this.parkingFee = parkingFee;
        this.wifi = wifi;
        this.laundry = laundry;
        this.view = view;
        this.smoking = smoking;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.picture = picture;
        this.owner = owner;
    }

    @Column(name="street_name")
    private String streetName;



    public int getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(int weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public int getRoomSquareFootage() {
        return roomSquareFootage;
    }

    public void setRoomSquareFootage(int roomSquareFootage) {
        this.roomSquareFootage = roomSquareFootage;
    }

    public boolean isBathIncluded() {
        return bathIncluded;
    }

    public void setBathIncluded(boolean bathIncluded) {
        this.bathIncluded = bathIncluded;
    }

    public boolean isShowerIncluded() {
        return showerIncluded;
    }

    public void setShowerIncluded(boolean showerIncluded) {
        this.showerIncluded = showerIncluded;
    }

    public float getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(float weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private int zipcode;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @Column(name = "sharing_type", nullable = false)
    private String sharingType;

    @Column(name = "number_of_rooms", nullable = false)
    private int numberOfRooms;

    @Column(name = "total_square_footage", nullable = false)
    private int totalSquareFootage;

    @Column(name = "parking", nullable = false)
    private boolean parking;

    @Column(name = "parking_fee", nullable = false)
    private float parkingFee;

    @Column(name = "wifi", nullable = false)
    private String wifi;

    @Column(name="laundry")
    private boolean laundry;

    @Column(name="view")
    private String view;

    @Column(name="smoking")
    private boolean smoking;

    @Column(name="mon")
    private boolean mon;

    @Column(name="tue")
    private boolean tue;

    @Column(name="wed")
    private boolean wed;

    @Column(name="thu")
    private boolean thu;

    @Column(name="fri")
    private boolean fri;

    @Column(name="sat")
    private boolean sat;

    @Column(name="sun")
    private boolean sun;

    @Column(name="picture")
    private String picture;

    @Column(name = "weekend_price", nullable = false)
    private int weekendPrice;

    @Column(name="room_square_footage")
    private int roomSquareFootage;

    @Column(name="bath_included")
    private boolean bathIncluded;

    @Column(name="shower_included")
    private boolean showerIncluded;

    @Column(name = "weekday_price", nullable = false)
    private float weekdayPrice;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
//    @Column(name="owner_id")
//    private int owner_id;

    @OneToMany(mappedBy="propertyId", targetEntity = Reservations.class, fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Reservations> reservations=new ArrayList<>();;
//    @PrePersist
//    private void prePersist() {
//        roomList.forEach( c -> c.setProperty(this));
//    }

    public Property() {
        super();
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSharingType() {
        return sharingType;
    }

    public void setSharingType(String sharingType) {
        this.sharingType = sharingType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getTotalSquareFootage() {
        return totalSquareFootage;
    }

    public void setTotalSquareFootage(int totalSquareFootage) {
        this.totalSquareFootage = totalSquareFootage;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public float getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(float parkingFee) {
        this.parkingFee = parkingFee;
    }

    public String isWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public boolean isLaundry() {
        return laundry;
    }

    public void setLaundry(boolean laundry) {
        this.laundry = laundry;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void addReservation(Reservations reservation){
        reservations.add(reservation);
        //reservation.setProperty(this);
        reservation.setPropertyId(this.propertyId);
    }

    @Override
    public String toString() {
        return "\nProperty{" +
                "city=" + city +
                ", street='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

}


