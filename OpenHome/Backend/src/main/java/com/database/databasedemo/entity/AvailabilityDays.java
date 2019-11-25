package com.database.databasedemo.entity;

import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;

@Entity
@Table(name="availability_days")
public class AvailabilityDays {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

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
}

