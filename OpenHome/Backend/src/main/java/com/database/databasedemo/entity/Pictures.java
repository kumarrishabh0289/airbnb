package com.database.databasedemo.entity;

import javax.persistence.*;

@Entity
@Table(name="pictures")
public class Pictures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="picture_url")
    private String pictureUrl;

    @ManyToOne
    private Property property;

}
