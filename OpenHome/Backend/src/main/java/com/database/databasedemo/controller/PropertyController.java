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
            //System.out.println("Request from frontend: "+property.getRoomList());
            propertyService.createProperty(property);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/property/remove/{property}",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> removeProperty(@PathVariable("property") int property) {
        propertyService.removeProperty(property);
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
    public ResponseEntity<?> changheAvailaibility(@RequestBody Property property) {
        //System.out.println("Request from frontend: "+property.getRoomList());
        propertyService.createProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
