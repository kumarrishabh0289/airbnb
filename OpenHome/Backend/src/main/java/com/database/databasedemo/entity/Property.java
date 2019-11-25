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

    @ManyToOne
    private Person owner;

    @OneToOne
    private Address address;

    @OneToOne
    private PropertyAdditionalOptions additionalOptions;

    @OneToOne
    private AvailabilityDays availabilityDays;

    @OneToMany(mappedBy="apartment", targetEntity = Room.class, fetch = FetchType.EAGER)
    private List<Room> roomList;

    @OneToMany(mappedBy="apartment", targetEntity = Pictures.class, fetch = FetchType.EAGER)
    private List<Pictures> pictureList;

    public Property() {
        super();
    }
}
