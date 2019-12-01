package com.database.databasedemo.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue
    private int id;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String name;

    private Date date;





    public Test() {

    }


    public Test(String name, Date date) {
        super();
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +

                ", date='" + date + '\'' +
                '}';
    }
}
