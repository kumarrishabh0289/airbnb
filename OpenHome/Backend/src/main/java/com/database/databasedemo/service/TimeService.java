package com.database.databasedemo.service;

import java.time.Clock;
import java.time.OffsetDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TimeService {
    OffsetDateTime currentTime=OffsetDateTime.now(Clock.systemUTC());;

    private TimeService(){

    }

    public OffsetDateTime getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(OffsetDateTime currentTime) {
        this.currentTime = currentTime;
    }
    public void run() {
        System.out.println("Running time service thread");
    }
}
