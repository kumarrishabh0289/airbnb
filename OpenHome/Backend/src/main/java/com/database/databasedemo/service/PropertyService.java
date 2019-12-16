package com.database.databasedemo.service;
import com.database.databasedemo.mail.SendMail;
import com.database.databasedemo.repository.PersonJPARepo;
import com.database.databasedemo.service.ReservationService;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Search;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    TimeService timeService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    @Qualifier("reservations")
    ReservationRepo reservationRepo;

    @Autowired
    ReservationService reservationService;

    @Autowired
    PersonJPARepo personJPARepo;

    public Property getProperty(int id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public List<Property> getAllProperties() {
        return propertyRepo.findAll();
    }

    public List<Property> getHostProperties(Person ownerId) {
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

    public void updateProperty(Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {



        Property p1 = getProperty(property.getPropertyId());
//
//        System.out.println("fri"+ p1.isFri());
//        System.out.println("description"+ property.getPropertyDescription());
        if(property.getPropertyDescription().equals("true")) {
            if (p1.isMon() == true && property.isMon() == false) {
                deductfor7Days(property, "MONDAY");
                 notDeducted(property,"MONDAY");
            }
            if (p1.isTue() == true && property.isTue() == false) {
                deductfor7Days(property,  "TUESDAY");
                notDeducted(property,"TUESDAY");
            }
            if (p1.isWed() == true && property.isWed() == false) {
                deductfor7Days(property,  "WEDNESDAY");
                notDeducted(property,"WEDNESDAY");
            }
            if (p1.isThu() == true && property.isThu() == false) {
                deductfor7Days(property,  "THURSDAY");
                notDeducted(property,"THURSDAY");
            }
            if (p1.isFri() == true && property.isFri() == false) {
                deductfor7Days(property,  "FRIDAY");
                notDeducted(property,"FRIDAY");
            }
            if (p1.isSat() == true && property.isSat() == false) {
                deductfor7Days(property,  "SATURDAY");
                notDeducted(property,"SATURDAY");
            }
            if (p1.isSun() == true && property.isSun() == false) {
                deductfor7Days(property, "SUNDAY");
                notDeducted(property,"SUNDAY");
            }
        }else{
            if (p1.isMon() == true && property.isMon() == false) {
                  notDeducted(property,"MONDAY");
            }
            if (p1.isTue() == true && property.isTue() == false) {
                notDeducted(property,"TUESDAY");
            }
            if (p1.isWed() == true && property.isWed() == false) {
                notDeducted(property,"WEDNESDAY");
            }
            if (p1.isThu() == true && property.isThu() == false) {
                notDeducted(property,"THURSDAY");
            }
            if (p1.isFri() == true && property.isFri() == false) {
                notDeducted(property,"FRIDAY");
            }
            if (p1.isSat() == true && property.isSat() == false) {
                notDeducted(property,"SATURDAY");
            }
            if (p1.isSun() == true && property.isSun() == false) {
                notDeducted(property,"SUNDAY");
            }
        }
        
        p1.setMon(property.isMon());
        p1.setTue(property.isTue());
        p1.setWed(property.isWed());
        p1.setThu(property.isThu());
        p1.setFri(property.isFri());
        p1.setSat(property.isSat());
        p1.setSun(property.isSun());
        p1.setWeekdayPrice(property.getWeekdayPrice());
        p1.setWeekendPrice(property.getWeekendPrice());
        propertyRepo.save(p1);

        Person p = personJPARepo.findById(p1.getOwner().getId());
        String receiver = p.getEmail();

        if(!receiver.equals("")) {
            SendMail y = new SendMail();
            y.sendEmail("You have updated Property in Open Home", receiver,
                    "You have updated Property in Open Home.\n\n For more details check your dashboard\n\n " +
                            "Thanks and Regards, \n OpenHome Team");
        }
    }

    public void deductfor7Days(Property property, String day) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        System.out.println("Get day of a week");
        OffsetDateTime nextSevenDate = timeService.getCurrentTime().plusDays(7);
        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        for (OffsetDateTime date = timeService.getCurrentTime(); date.compareTo(nextSevenDate) <= 0; date = date.plusHours(24)) {

            System.out.println("Get day of a week"+date.getDayOfWeek());
            if(day.equals(date.getDayOfWeek().toString())) {
                res = dateComparision(date, property.getPropertyId());
//                System.out.println(date.getDayOfWeek());
//                System.out.println(res);
            }
            if(res.size()>0)
                finaList.addAll(res);
        }

        for(int i=0;i<finaList.size();i++){
            double penaltyPrice = (0.15) * finaList.get(i).getBookedPrice();
            Reservations reservations = reservationService.getReservation((int)finaList.get(i).getId());
            reservations.setPenaltyValue((float)penaltyPrice);
            reservations.setPenaltyReason("Penalty Paid by Host");
            reservations.setStatus("Available");
            reservationRepo.save(reservations);

            String receiver = personJPARepo.findById(reservations.getGuestId()).getEmail();

            if(!receiver.equals("")) {
                SendMail y = new SendMail();
                y.sendEmail("Your Booking got Cancelled in Open Home", receiver,
                        "Dear Customer, \n\n Your Booking got Cancelled in Open Home\n\n For more details check your dashboard\n\n " +
                                "Thanks and Regards, \n OpenHome Team");
            }



        }
    }

    public void notDeducted(Property property, String day) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        OffsetDateTime nextSevenDate = timeService.getCurrentTime().plusDays(7);
        OffsetDateTime nextEntireYear = timeService.getCurrentTime().plusDays(365);
        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        for (OffsetDateTime date = nextSevenDate; date.compareTo(nextEntireYear) <= 0; date = date.plusHours(24)) {

            if(day.equals(date.getDayOfWeek().toString())) {
                res = dateComparision(date, property.getPropertyId());
            }

            if(res.size()>0)
                finaList.addAll(res);
        }

        for(int i=0;i<finaList.size();i++){
            Reservations reservations = reservationService.getReservation((int)finaList.get(i).getId());
            reservations.setStatus("Available");
            reservationRepo.save(reservations);

            String receiver = personJPARepo.findById(reservations.getGuestId()).getEmail();

            if(!receiver.equals("")) {
                SendMail y = new SendMail();
                y.sendEmail("Your Booking got Cancelled in Open Home", receiver,
                        "Dear Customer, \n\n Your Booking got Cancelled in Open Home\n\n For more details check your dashboard\n\n " +
                                "Thanks and Regards, \n OpenHome Team");
            }

        }

    }

    public void deductfor7DaysRemovalProperty(Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        System.out.println("Get day of a week");
        OffsetDateTime nextSevenDate = timeService.getCurrentTime().plusDays(7);
        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        for (OffsetDateTime date = timeService.getCurrentTime(); date.compareTo(nextSevenDate) <= 0; date = date.plusHours(24)) {

           // System.out.println("Get day of a week"+date.getDayOfWeek());

            res = dateComparision(date, property.getPropertyId());

            if(res.size()>0)
                finaList.addAll(res);
        }

        for(int i=0;i<finaList.size();i++){
            double penaltyPrice = (0.15) * finaList.get(i).getBookedPrice();
            Reservations reservations = reservationService.getReservation((int)finaList.get(i).getId());
            reservations.setPenaltyValue((float)penaltyPrice);
            reservations.setPenaltyReason("Penalty Paid by Host");
            reservations.setPropertyId(0);
           // reservations.setStatus("Cancelled due to Removal of Property");
            reservations.setStatus("Available");
            reservationRepo.save(reservations);

            String receiver = personJPARepo.findById(reservations.getGuestId()).getEmail();

            if(!receiver.equals("")) {
                SendMail y = new SendMail();
                y.sendEmail("Your Booking got Cancelled in Open Home", receiver,
                        "Dear Customer, \n\n Your Booking got Cancelled in Open Home\n\n For more details check your dashboard\n\n " +
                                "Thanks and Regards, \n OpenHome Team");
            }
        }
    }

    public void notDeductedRemovalProperty(Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        OffsetDateTime nextSevenDate = timeService.getCurrentTime().plusDays(7);
        OffsetDateTime nextEntireYear = timeService.getCurrentTime().plusDays(365);
        List<Reservations> finaList = new ArrayList<>();
        List<Reservations> res = new ArrayList<>();
        for (OffsetDateTime date = nextSevenDate; date.compareTo(nextEntireYear) <= 0; date = date.plusHours(24)) {
                res = dateComparision(date, property.getPropertyId());

            if(res.size()>0)
                finaList.addAll(res);
        }

        for(int i=0;i<finaList.size();i++){
            Reservations reservations = reservationService.getReservation((int)finaList.get(i).getId());
        //    reservations.setStatus("Cancelled due to Removal of Property");
            reservations.setStatus("Available");
            reservationRepo.save(reservations);

            String receiver = personJPARepo.findById(reservations.getGuestId()).getEmail();

            if(!receiver.equals("")) {
                SendMail y = new SendMail();
                y.sendEmail("Your Booking got Cancelled in Open Home", receiver,
                        "Dear Customer, \n\n Your Booking got Cancelled in Open Home\n\n For more details check your dashboard\n\n " +
                                "Thanks and Regards, \n OpenHome Team");
            }

        }

    }

    public void createProperty(Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {


        propertyRepo.save(property);

        Person p = personJPARepo.findById(property.getOwner().getId());
        String receiver = p.getEmail();

        if(!receiver.equals("")) {
            SendMail y = new SendMail();
            y.sendEmail("You have created new Property in Open Home", receiver,
                    "You have created new Property in Open Home.\n\n For more details check your dashboard\n\n " +
                            "Thanks and Regards, \n OpenHome Team");
        }
    }

    public void removeEntireProperty(Property property) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        Property p1 = getProperty(property.getPropertyId());

        if(property.getPropertyDescription().equals("true")) {
            deductfor7DaysRemovalProperty(p1);
            notDeductedRemovalProperty(p1);
        }
        else{
            notDeductedRemovalProperty(p1);
        }

        p1.setStatus("Removed");
        propertyRepo.save(p1);

        Person p = personJPARepo.findById(p1.getOwner().getId());
        String receiver = p.getEmail();

        if(!receiver.equals("")) {
            SendMail y = new SendMail();
            y.sendEmail("You have removed Property in Open Home", receiver,
                    "You have removed Property in Open Home.\n\n For more details check your dashboard\n\n " +
                            "Thanks and Regards, \n OpenHome Team");
        }
    }

    public void removeProperty(int id) {
        propertyRepo.deleteById(id);
    }


    public List<Reservations> dateComparision(OffsetDateTime date, int propertyId) {

        System.out.println("dataComparision");
        List<Reservations> reserv = reservationRepo.findAll(new Specification<Reservations>() {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reservations> query = builder.createQuery(Reservations.class);
            Root<Reservations> root = query.from(Reservations.class);

            @Override
            public Predicate toPredicate(Root<Reservations> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), date));
                predicates.add(cb.greaterThanOrEqualTo(root.get("endDate"), date));
                predicates.add(cb.equal(root.get("propertyId"), propertyId));
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
        return reserv;
    }
}
