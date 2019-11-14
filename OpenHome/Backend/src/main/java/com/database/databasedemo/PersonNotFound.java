package com.database.databasedemo;

public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String exception) {
        super(exception);
    }
}
