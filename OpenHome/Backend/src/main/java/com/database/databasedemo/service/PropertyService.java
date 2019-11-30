package com.database.databasedemo.service;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    public Property getProperty(int id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public List<Property> getAllProperties(){
        return propertyRepo.findAll();
    }

    public List<Property> getHostProperties(Person ownerId){
//        List<Property> allProperties=propertyRepo.findAll();
//        List<Property> hostProperties=new ArrayList<>();
//        for(Property p: allProperties){
//            if(p.getOwner().getId()==guest.getId()){
//                hostProperties.add(p);
//            }
//        }
//        return hostProperties;
        return propertyRepo.findByOwner(ownerId);
    }

    public void createProperty(Property property){
        propertyRepo.save(property);
    }

    public void removeProperty(int id){
        propertyRepo.deleteById(id);
    }
}
