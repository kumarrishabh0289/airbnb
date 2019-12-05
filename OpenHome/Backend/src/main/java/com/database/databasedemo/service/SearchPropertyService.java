package com.database.databasedemo.service;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.repository.PersonJPARepo;
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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.database.databasedemo.entity.Property;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.*;
//import java.util.function.Predicate;

@Service
@Transactional
public class SearchPropertyService {

    @Autowired
    @Qualifier("property")
    PropertyRepo propertyRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    @Qualifier("reservations")
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

        //  Reservations reservations = reservationService.getReservation(1);

        List<Property> properties = propertyRepo.findAll(new Specification<Property>() {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Property> query = builder.createQuery(Property.class);
            Root<Property> root = query.from(Property.class);

            @Override
            public Predicate toPredicate(Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                // If designation is specified in filter, add equal where clause
                if (!filter.getCity().equals("")) {
                    if (isNumeric(filter.getCity())) {
                        predicates.add(cb.equal(root.get("zipcode"), Integer.parseInt(filter.getCity())));
                    } else {
                        predicates.add(cb.like(root.get("city"), "%" + filter.getCity() + "%"));
                    }
                }

                if (!filter.getSharingType().equals("")) {
                    predicates.add(cb.equal(root.get("sharingType"), filter.getSharingType()));
                }

                if (!filter.getPropertyType().equals("")) {
                    predicates.add(cb.equal(root.get("propertyType"), filter.getPropertyType()));
                }

                if (!filter.getPropertyDescription().equals("")) {


                    String[] splited = filter.getPropertyDescription().split("\\s+");

                    for (int i = 0; i < splited.length; i++) {
                        predicates.add(cb.like(root.get("propertyDescription"), "%" + splited[i] + "%"));
                    }

                }

                if (!filter.getWifi().equals("")) {
                    predicates.add(cb.equal(root.get("wifi"), filter.getWifi()));
                }

                predicates.add(cb.equal(root.get("status"), "Created"));

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });

        Calendar start = Calendar.getInstance();
        start.setTime(filter.getStartDate());
        start.add(Calendar.DATE, 1);
        Calendar end = Calendar.getInstance();
        end.setTime(filter.getEndDate());
        end.add(Calendar.DATE, 1);


        int weekDcntr = 0;
        int weekEcntr = 0;
        OffsetDateTime now1 = OffsetDateTime.now(ZoneOffset.UTC);
        HashSet<Integer> hashSet = new HashSet<>();
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

            hashSet.add(date.getDay());
            System.out.println("date" + date);
            System.out.println("date" + date.getDay());
            if (date.getDay() == 0 || date.getDay() == 6) {
                weekEcntr++;
            }

            if (date.getDay() == 1 || date.getDay() == 2 || date.getDay() == 3 || date.getDay() == 4 || date.getDay() == 5) {
                weekDcntr++;
            }
        }

        System.out.println("Hashset" + hashSet);
        if (!filter.getPriceRange().equals("")) {
            String str = filter.getPriceRange();
            String[] result = str.split(" to ");
            ListIterator<Property> iter = properties.listIterator();
            while (iter.hasNext()) {

                Property p = iter.next();
                float weekdayP = p.getWeekdayPrice();
                float weekendP = p.getWeekendPrice();
                float finalPrice = (weekDcntr * weekdayP) + (weekendP * weekEcntr);
                // weekend check needs to be added depending upon the user selection of the dates
                // If he selected for weekends then consider weekend if weekday then only weekday if both then only below
                if (finalPrice < Float.parseFloat(result[0]) || finalPrice > Float.parseFloat(result[1])) {
                    iter.remove();
                }
            }
        }

        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        Date current = filter.getStartDate();
        while (!current.after(filter.getEndDate())) {
            res = dateComparision(current,filter);
            if(res.size()>0)
             finaList.addAll(res);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);
            calendar.add(Calendar.DATE, 1);
            current = calendar.getTime();
        }

        if(finaList.size()>0)
        {
            for(int i=0;i<finaList.size();i++){
                ListIterator<Property> iter = properties.listIterator();
                while(iter.hasNext()){
                    Property p = iter.next();

                    if(p.getPropertyId() == finaList.get(i).getPropertyId()){
                        iter.remove();
                    }
                }
            }
        }


        List<Property> properties1 = new ArrayList<>();


        System.out.println(properties.size());
        for (int i = 0; i < properties.size(); i++) {
            Property p = properties.get(i);

            if (p.isSun() && !hashSet.contains(0)) {
                properties1.add(p);
            }
            else if (p.isMon() && !hashSet.contains(1)) {
                properties1.add(p);
            }
            else if (p.isTue() && !hashSet.contains(2)) {
                properties1.add(p);
            }
            else if (p.isWed() && !hashSet.contains(3)) {
                properties1.add(p);
            }
            else if (p.isThu() && !hashSet.contains(4)) {
                properties1.add(p);
            }
            else if (p.isFri() && !hashSet.contains(5)) {
                properties1.add(p);
            }
            else if (p.isSat() && !hashSet.contains(6)) {
                properties1.add(p);
            }
        }

        return properties1;
    }



    public  List<Reservations> dateComparision(Date date,Search filter){

        OffsetDateTime offsetDateTime = date.toInstant()
                .atOffset(ZoneOffset.UTC);

        List<Reservations> reserv = reservationRepo.findAll(new Specification <Reservations >() {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reservations> query = builder.createQuery(Reservations.class);
            Root<Reservations> root = query.from(Reservations.class);

            @Override
            public Predicate toPredicate(Root<Reservations> root, CriteriaQuery< ?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), offsetDateTime));
                predicates.add(cb.greaterThan(root.get("endDate"), offsetDateTime));
                predicates.add(cb.notEqual(root.get("status"), "Available"));
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });

        return reserv;

    }

}