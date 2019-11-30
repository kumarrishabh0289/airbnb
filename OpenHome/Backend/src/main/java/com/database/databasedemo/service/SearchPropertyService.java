package com.database.databasedemo.service;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.SearchRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.database.databasedemo.entity.Property;

import java.util.*;
//import java.util.function.Predicate;

@Service
@Transactional
public class SearchPropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    @PersistenceContext
    EntityManager entityManager;

    public List<Property> searchProperties(){
        return propertyRepo.findAll();
    }

    public List<Property> searchPropertiesByCity(String city){
        return propertyRepo.findByCityLike("%"+city+"%");
    }

    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }

    public List<Property> retrievePropertiesByCriteria(Search filter) {

        List<Property> properties = propertyRepo.findAll(new Specification <Property >() {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Property> query = builder.createQuery(Property.class);
            Root<Property> root = query.from(Property.class);

            @Override
            public Predicate toPredicate(Root<Property> root, CriteriaQuery< ?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                // If designation is specified in filter, add equal where clause
                if (filter.getCity() != null) {
                    if(isNumeric(filter.getCity())) {
                        predicates.add(cb.equal(root.get("zipcode"), Integer.parseInt(filter.getCity())));
                    }
                    else{
                        predicates.add(cb.like(root.get("city"), "%"+filter.getCity()+"%"));
                    }
                }

                if (filter.getSharingType() != null) {
                    predicates.add(cb.equal(root.get("sharingType"), filter.getSharingType()));
                }

                if (filter.getPropertyType() != null) {
                    predicates.add(cb.equal(root.get("propertyType"), filter.getPropertyType()));
                }

                if (filter.getPropertyDescription() != null) {
                    predicates.add(cb.like(root.get("propertyDescription"),"%"+filter.getPropertyDescription()+"%"));
                }

                predicates.add(cb.equal(root.get("wifi"),filter.getWifi()));

                  return cb.and(predicates.toArray(new Predicate[0]));
            }
        });

        // Below logic is for Validating the price of the Room
        HashSet<Integer> hashSet = new HashSet<Integer>();
//        Date current = filter.getStartDate();
//        while (current.before(filter.getEndDate())) {
//            System.out.println(current);
//            hashSet.add(current.getDay());
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(current);
//            calendar.add(Calendar.DATE, 1);
//            current = calendar.getTime();
//
//            if(hashSet.size() > 7)
//                break;
//        }

        Calendar start = Calendar.getInstance();
        start.setTime(filter.getStartDate());
        Calendar end = Calendar.getInstance();
        end.setTime(filter.getEndDate());
        boolean datemiss = false;
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            System.out.println(date);
            hashSet.add(date.getDay());
            datemiss = true;
//            if(hashSet.size() > 7)
//                break;
        }

//        if(!datemiss)
//            return properties;

        //now, hashset contains the days from the entered date
        System.out.println("Hashset"+hashSet);

        // Below logic is for Validating the price of the Room
        System.out.println("list" + properties);
        if(filter.getPriceRange() != null)
        {
            for (int i = 0; i < properties.size(); i++) {
                System.out.println("list" + properties);
                String str = filter.getPriceRange();
                String[] result = str.split(" to ");

                System.out.println(Integer.parseInt(result[0]));
                System.out.println(Integer.parseInt(result[1]));
            }
        }


        return properties;
    }

}