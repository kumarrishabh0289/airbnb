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

    @Column(name = "room_description", nullable = false, length = 100)
    private String roomDescription;

    @Column(name = "weekend_price", nullable = false)
    private int weekendPrice;

    @Column(name="square_footage")
    private int squareFootage;

    @Column(name="bath_included")
    private boolean bathIncluded;

    @Column(name="shower_included")
    private boolean shower_included;

    @Column(name = "weekday_price", nullable = false)
    private float weekdayPrice;


    @OneToMany(mappedBy="room", targetEntity = Reservations.class, fetch = FetchType.EAGER)
    private List<Reservations> reservationList;

    @ManyToOne
    private Property property;

    public Room() {
        super();
    }


}
