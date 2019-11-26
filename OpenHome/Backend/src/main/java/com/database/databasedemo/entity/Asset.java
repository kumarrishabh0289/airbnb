package com.database.databasedemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "asset")
@NamedQuery(name = "find_all_asset", query = "select a from Asset a")
public class Asset {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String city;
    private String image;
    private int owner;


    public Asset() {

    }

    public Asset(String name, String city, String image, int owner) {
        this.name = name;
        this.city = city;
        this.image = image;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "\nAsset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", image='" + image + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
