package com.database.databasedemo;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.service.PropertyService;


import com.database.databasedemo.service.ReservationService;
import com.database.databasedemo.service.SearchPropertyService;


//import com.database.databasedemo.repository.AssetSpringDataRepo;


import com.database.databasedemo.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "com.database.databasedemo.entity")
public class OpenHomeApplication implements CommandLineRunner {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonSpringDataRepo repo;

    @Autowired
    PropertyService propertyService;

    @Autowired
    SearchPropertyService searchPropertyService;

    @Autowired
    ReservationService reservationService;
    public static void main(String[] args) {
        SpringApplication.run(OpenHomeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Insert  -> {}",
                repo.save(new Person("Suresh", "123","user","5789564578956256","895","2025/11/02","yes","suresh@mail.com")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Ramesh", "123","user","5789564578956256","895","2025/11/02","yes","ramesh@mail.com")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Rishabh", "123","user","5789564578956256","895","2025/11/02","yes","rishabh0289@gmail.com")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Ayushi", "123","user","5789564578956256","895","2025/11/02","yes","ayushi.1511@gmail.com")));

        logger.info("Find By ID -> {}", repo.findById(1));
        repo.deleteById(1);
        logger.info("Find All -> {}", repo.findAll());
        Person p=new Person("Prachi", "123","user","5789564578956256","895","2025/11/02","yes","Prachi@mail.com");
        repo.save(p);



        logger.info("Find By ID -> {}", repo.findById(2));
        repo.deleteById(2);
        Property newProp=new Property("San Jose Property notes in the list","North First","San Jose","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,p,40,50,false,true,false,true,true,false,true,"Yes","Created");
        Property newProp2=new Property("San Jose Property1 yet to not in the list","North First 1","San Jose 1","California",95113,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Partial",5,1200,p,10,110,true,false,false,true,true,true,true,"No","Created");

        Property newProp1=new Property("Sunnyvale Property is one of the wonderful city","North First","Sunnyvale","California",95112,"https://cdn.cnn.com/cnnnext/dam/assets/150511105029-airbnb-architecture--fox-island-full-169.jpg","Condo","Partial",2,900,p,12,24,true,true,false,true,true,false,false,"Yes","Created");

        logger.info("Insert  Property-> {}");

        propertyService.createProperty(newProp);
        propertyService.createProperty(newProp1);
        propertyService.createProperty(newProp2);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2019-11-30");
        OffsetDateTime booking_date = date.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println("Date "+date);
        System.out.println("OffsetDateTime "+booking_date);
        date = format.parse("2019-12-06");
        OffsetDateTime start_date = date.toInstant()
                .atOffset(ZoneOffset.UTC);

        date = format.parse("2019-12-09");
        OffsetDateTime end_date = date.toInstant()
                .atOffset(ZoneOffset.UTC);



        Reservations newReserve= new Reservations(100, 30,50,booking_date, start_date,end_date, 3,2,"33 S San joSe","AC HOTEL" ,"Booked" );
        reservationService.createReservations(newReserve);

        Reservations newReserve1= new Reservations(100, 30,50,booking_date, start_date,end_date, 3,3,"1338 The Alameda", "Motel" , "Booked");
        reservationService.createReservations(newReserve1);



        logger.info("Find property with property id 1{} ->",propertyService.getProperty(1).toString());

        logger.info("Find All -> {}", propertyService.getAllProperties());

        //logger.info("Find reservation with reservation id 1{} ->",reservationService.getReservation(1).toString());

        logger.info("Find All reservations -> {}", reservationService.getAllReservations());




    }
}
