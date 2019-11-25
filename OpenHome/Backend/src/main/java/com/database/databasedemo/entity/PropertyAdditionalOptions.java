package com.database.databasedemo.entity;

import javax.persistence.*;

@Entity
@Table(name="apartment_additional_options")
public class PropertyAdditionalOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="laundry")
    private boolean laundry;

    @Column(name="view")
    private String view;

    @Column(name="smoking")
    private boolean smoking;
}
