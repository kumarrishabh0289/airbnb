package com.database.databasedemo;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JPADemoApplication implements CommandLineRunner {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonSpringDataRepo repo;
    //PersonJPARepo repo;

    @Autowired
    PropertyService propertyService;
    public static void main(String[] args) {
        SpringApplication.run(JPADemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Insert  -> {}",
                repo.save(new Person("Suresh", "123")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Ramesh", "123")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Ayushi", "123")));
        logger.info("Insert  -> {}",
                repo.save(new Person("Rishabh", "123")));
        logger.info("Find By ID -> {}", repo.findById(1));
        repo.deleteById(1);
        logger.info("Find All -> {}", repo.findAll());
        Person p=new Person("Prachi", "123");
        repo.save(p);
        Property newProp=new Property("My Property","North First","San Jose","California",95112,"Condo","Full",2,900,p);
        logger.info("Insert  Property-> {}");

        propertyService.createProperty(newProp);
        logger.info("Find property with property id 1{} ->",propertyService.getProperty(1).toString());
        logger.info("Find All -> {}", propertyService.getAllProperties());

        logger.info("Find properties for owner with id 5->{}",propertyService.getHostProperties(p));


    }
}
