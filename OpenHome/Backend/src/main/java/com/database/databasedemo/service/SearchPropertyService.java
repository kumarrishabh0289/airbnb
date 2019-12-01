package com.database.databasedemo.service;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
//import java.util.function.Predicate;

@Service
@Transactional
public class SearchPropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ReservationRepo reservationRepo;

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

    public List<Property> retrievePropertiesByCriteria(Search filter) throws ParseException {

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
        boolean weekD = false;
        boolean weekE = false;
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            System.out.println(date);
            hashSet.add(date.getDay());

//            if(hashSet.size() > 7)
//                break;
        }

        if(hashSet.contains(0) || hashSet.contains(6))
            weekE = true;

        if(hashSet.contains(1) || hashSet.contains(2) || hashSet.contains(3) || hashSet.contains(4) || hashSet.contains(5))
            weekD = true;

        //now, hashset contains the days from the entered date
        System.out.println("Hashset"+hashSet);

        // Below logic is for Validating the price of the Room
        System.out.println("list" + properties);
        if(filter.getPriceRange() != null)
        {
//            String str = filter.getPriceRange();
//            String[] result = str.split(" to ");
//            ListIterator<Property> iter = properties.listIterator();
//            while(iter.hasNext()){
//
//                Property p = iter.next();
//                float weekdayP = p.getWeekdayPrice();
//                float weekendP = p.getWeekdayPrice();
//
//                // weekend check needs to be added depending upon the user selection of the dates
//                // If he selected for weekends then consider weekend if weekday then only weekday if both then only below.
//                if(weekD && weekE) {
//                    if ((weekdayP <= Integer.parseInt(result[0]) || weekdayP >= Integer.parseInt(result[1])) ||
//                            (weekendP <= Integer.parseInt(result[0]) || weekendP >= Integer.parseInt(result[1]))) {
//                        iter.remove();
//                    }
//                }
//                else if(weekD){
//                    if ((weekdayP <= Integer.parseInt(result[0]) || weekdayP >= Integer.parseInt(result[1]))) {
//                        iter.remove();
//                    }
//                }
//                else{
//                    if ((weekendP <= Integer.parseInt(result[0]) || weekendP >= Integer.parseInt(result[1]))) {
//                        iter.remove();
//                    }
//                }
//            }
        }



        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        Date current = filter.getStartDate();
        while (!current.after(filter.getEndDate())) {
            System.out.println(current);
            res = dateComparision(current,filter);

            if(res.size()>0)
             finaList.addAll(res);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);
            calendar.add(Calendar.DATE, 1);
            current = calendar.getTime();
        }

//        String date = filter.getStartDate().toString();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date date1 = sdf1.parse(date);
//        java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());
//        System.out.println("name"+"date"+sqlStartDate);

       System.out.println(res);
        System.out.println(finaList.size());
//        if(finaList.size()>0)
//            System.out.println("finaList"+finaList.size());

        return properties;
    }



    public  List<Reservations> dateComparision(Date date,Search filter){

        OffsetDateTime offsetDateTime = date.toInstant()
                .atOffset(ZoneOffset.UTC);

        System.out.println("offsetDateTime=="+offsetDateTime);

        List<Reservations> reserv = reservationRepo.findAll(new Specification <Reservations >() {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reservations> query = builder.createQuery(Reservations.class);
            Root<Reservations> root = query.from(Reservations.class);

            @Override
            public Predicate toPredicate(Root<Reservations> root, CriteriaQuery< ?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), offsetDateTime));
                predicates.add(cb.greaterThanOrEqualTo(root.get("endDate"), offsetDateTime));
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });



        if(reserv.size()>0) {
            System.out.println(reserv.get(0).getPropertyId());
            System.out.println(reserv.get(0).getBookedPrice());
        }
        return reserv;
    }

}