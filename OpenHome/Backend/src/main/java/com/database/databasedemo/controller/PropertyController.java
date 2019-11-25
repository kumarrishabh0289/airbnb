package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Property;
import com.database.databasedemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createProperty(@RequestBody Property property) {
            propertyService.createProperty(property);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Property> getAllProperties(@RequestBody Property property) {
        return propertyService.getAllProperties();
    }
}
