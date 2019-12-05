package com.database.databasedemo.controller;

import com.database.databasedemo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class TimeController {

    @Autowired
    TimeService timeService;
    @GetMapping("/admin/time")
    public OffsetDateTime getTime() {
        return timeService.getCurrentTime();
    }

    @GetMapping("/admin/date")
    public LocalDate getDate() {
        return timeService.getCurrentTime().minusHours(8).toLocalDate();
    }

    @GetMapping("/admin/time/hours")
    public long getOffsetHours() {
        return timeService.getHours();
    }

    @GetMapping("/admin/time/mins")
    public long getOffsetMins() {
        return timeService.getMins();
    }

    @PostMapping("/admin/time/addoffset/{hours}/{mins}")
    public ResponseEntity<?> addOffsetToTime(@PathVariable("hours") long hours,@PathVariable("mins") long mins) {
        timeService.addHours(hours);
        timeService.addMins(mins);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
