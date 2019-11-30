package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.repository.PersonNotFound;
//import com.database.databasedemo.service.PersonSpringDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import com.database.databasedemo.service.SearchPropertyService;
import com.database.databasedemo.entity.Property;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class SearchController {


    @Autowired
    SearchPropertyService searchPropertyService;
//    private PersonSpringDataRepo repo;

//    @GetMapping("/search/property")
//    public List<Property> retrivePropertyDetails(@RequestBody Property property) {
//        System.out.println(property.getCity());
//        return searchPropertyService.searchPropertiesByCity(property.getCity());
//
//
//      //  return searchPropertyService.searchProperties();
//    }

    @PostMapping("/search/property")
    public List<Property> retrivePropertyDetails(@RequestBody Search property) throws ParseException {
//    public List<Property> retrivePropertyDetails(@RequestBody Property property, Property property1) {
//        System.out.println(property.getCity());
//        System.out.println(property.getSharingType());
//        System.out.println(property.getPropertyType());
//        System.out.println(property1.getPropertyDescription());
//        System.out.println(property.isWifi());

            System.out.println(property.getPropertyType());
                System.out.println(property.getPriceRange());
            System.out.println(property.getSharingType());
            System.out.println(property.getCity());
            System.out.println(property.getEndDate());
            System.out.println(property.getStartDate());
//            System.out.println(property.getStartDate().getDay());
            System.out.println(property.getWifi());
            System.out.println(property.getPropertyDescription());


//        Calendar start = Calendar.getInstance();
////        start.setTime(property.getStartDate());
////        Calendar end = Calendar.getInstance();
////        end.setTime(property.getEndDate());
////
////        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
////            System.out.println(date);
////        }



//        Date current = property.getStartDate();
//        while (current.before(property.getEndDate())) {
//            System.out.println(current);
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(current);
//            calendar.add(Calendar.DATE, 1);
//            current = calendar.getTime();
//        }

//        System.out.println(property1);
       // return searchPropertyService.retrievePropertiesByCriteria(property);

         return searchPropertyService.retrievePropertiesByCriteria(property);
        //  return searchPropertyService.searchProperties();
    }
}