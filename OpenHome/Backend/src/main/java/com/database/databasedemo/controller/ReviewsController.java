package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.service.PropertyService;
import com.database.databasedemo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ReviewsController {

    @Autowired
    ReviewsService reviewsService;

    @Autowired
    private PersonSpringDataRepo personRepo;

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/review/add")
    public ResponseEntity<?> addReview(@RequestBody Map<String, String> payload) throws ParseException {
        System.out.println("payload"+payload);
        String propertyId = (String)payload.get("propertyId");
        int propertyIdentifier=Integer.parseInt(propertyId);
        String review = (String)payload.get("review");
        int rating=Integer.parseInt(review);

        Property property=propertyService.getProperty(propertyIdentifier);
        if(property==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Person person1=property.getOwner();
        if(person1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reviewsService.addReview(person1.getId(),rating);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/review/hostadd")
    public ResponseEntity<?> addHostReview(@RequestBody Map<String, String> payload) throws ParseException {
        System.out.println("payload"+payload);
        String personId = (String)payload.get("personId");
        int person=Integer.parseInt(personId);
        String review = (String)payload.get("review");
        int rating=Integer.parseInt(review);
        reviewsService.addReview(person,rating);
        Person person1=personRepo.findById(person).orElse(null);
        if(person1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/review/{id}")
    public float getAverageReview(@PathVariable int id) {
        System.out.println("id" + id);
        return reviewsService.getAverageReviews(id);
    }
}
