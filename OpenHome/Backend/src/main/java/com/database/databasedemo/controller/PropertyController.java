package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.service.PropertyService;
import com.database.databasedemo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;





    @PostMapping("/property/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createProperty(@RequestBody Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
            //System.out.println("Request from frontend: "+property.getRoomList());
            propertyService.createProperty(property);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/property/remove/{property}",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> removeProperty(@PathVariable("property") Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        propertyService.removeEntireProperty(property);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/property/all")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/property/{id}")
    public Property getProperty(@PathVariable int id) {

            System.out.println("id"+id);
        return propertyService.getProperty(id);
    }

    @GetMapping("/property/owner/{ownerId}")
    public List<Property> getOwnerProperties(@PathVariable String ownerId) {
        System.out.println(ownerId);
        Person person=new Person();
        person.setId((Integer.parseInt(ownerId)));
        return propertyService.getHostProperties(person);
    }

    @PostMapping("/property/availability")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> changeAvailaibility(@RequestBody Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        propertyService.updateProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/property/delete")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> deleteProperty(@RequestBody Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        propertyService.removeEntireProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }





}
