package com.database.databasedemo.repository;

public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String exception) {
        super(exception);
    }
}
