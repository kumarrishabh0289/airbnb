package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int apartmentId;

    @Column(name = "apartment_description", nullable = false, length = 100)
    private String apartmentDescription;

    @Column(name="street_name")
    private String streetName;

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
    private boolean wifi;

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

    @ManyToOne
    private Person owner;

    @OneToMany(mappedBy="apartment", targetEntity = Room.class, fetch = FetchType.EAGER)
    private List<Room> roomList;


    public Property() {
        super();
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(String apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
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

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
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

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
