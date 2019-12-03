package com.database.databasedemo.controller;

import com.database.databasedemo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/admin/time/{hours}")
    public ResponseEntity<?> addOffsetToTime(@PathVariable("hours") long hours) {
        timeService.setCurrentTime(timeService.getCurrentTime().plusHours(hours));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
