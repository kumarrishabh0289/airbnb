package com.database.databasedemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "person")
@NamedQuery(name = "find_all", query = "select p from Person p")
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {

    }
    public Person(int id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Person( String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
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
                ", password='" + password + '\'' +
                '}';
    }
}
