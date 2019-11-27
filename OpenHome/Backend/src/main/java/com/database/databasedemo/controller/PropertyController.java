package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
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
    @PostMapping("/property/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createProperty(@RequestBody Property property) {
            propertyService.createProperty(property);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/property/all")
    public List<Property> getAllProperties(@RequestBody Property property) {
        return propertyService.getAllProperties();
    }

    @GetMapping("/property/{id}")
    public Property getProperty(@PathVariable int id) {
        return propertyService.getProperty(id);
    }

    @GetMapping("/property/owner/{ownerId}")
    public List<Property> getOwnerProperties(@PathVariable Person ownerId) {
        System.out.println(ownerId);
        return propertyService.getHostProperties(ownerId);
    }
}
