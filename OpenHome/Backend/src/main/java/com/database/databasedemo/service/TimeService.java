package com.database.databasedemo.service;

import java.time.Clock;
import java.time.OffsetDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TimeService {
    //OffsetDateTime currentTime;
    long hours=0;
    long mins=0;

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public void addHours(long hours) {
        this.hours += hours;
    }

    public long getMins() {
        return mins;
    }

    public void setMins(long mins) {
        this.mins = mins;
    }

    public void addMins(long mins) {
        this.mins += mins;
    }

    private TimeService(){

    }

    public OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now(Clock.systemUTC()).plusHours(this.hours).plusMinutes(this.mins);
    }

}
