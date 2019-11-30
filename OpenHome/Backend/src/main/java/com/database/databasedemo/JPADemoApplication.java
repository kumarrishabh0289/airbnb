package com.database.databasedemo;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.service.PropertyService;

import com.database.databasedemo.service.SearchPropertyService;

import com.database.databasedemo.entity.Asset;
import com.database.databasedemo.repository.AssetSpringDataRepo;
import com.database.databasedemo.repository.PersonSpringDataRepo;

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


    @Autowired
    AssetSpringDataRepo assetRepo;
    //PersonJPARepo repo;

    @Autowired
    PropertyService propertyService;

    @Autowired
    SearchPropertyService searchPropertyService;
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

//        Property newProp=new Property("My Property","North First","San Jose","California",95112,"Condo","Full",2,900,p);
//        Property newProp4=new Property("My Property","North First","San Jose","California",95112,"Condo","Full",2,900,p);
//        Property newProp1=new Property("My Property","North First","San Fernando","California",95112,"Condo","Partial",2,900,p);
//        Property newProp2=new Property("My Property","North First","San Francisco","California",95112,"House","Full",2,900,p);
//        Property newProp3=new Property("My Property","North First","San Francisco","California",5112,"House","Partial",2,900,p);


        Property newProp=new Property("San Jose Property","North First","San Jose","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,p);
        Property newProp1=new Property("Sunnyvale Property","North First","Sunnyvale","California",95112,"https://cdn.cnn.com/cnnnext/dam/assets/150511105029-airbnb-architecture--fox-island-full-169.jpg","Condo","Partial",2,900,p);

        logger.info("Insert  Property-> {}");

        propertyService.createProperty(newProp);

//        propertyService.createProperty(newProp2);
//        propertyService.createProperty(newProp3);
//        propertyService.createProperty(newProp4);


        logger.info("Find property with property id 1{} ->",propertyService.getProperty(1).toString());

        logger.info("Find All -> {}", propertyService.getAllProperties());
        Property searchProp=new Property("San Jose","Full");
       // logger.info("Search Property->{}",searchPropertyService.retrievePropertiesByCriteria(searchProp));

        logger.info("Find properties for owner with id 5->{}",propertyService.getHostProperties(p));



        logger.info("Find By ID -> {}", repo.findById(2));
        repo.deleteById(2);

        propertyService.createProperty(new Property("San Jose both less Property","North First","San Jose State","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,40,50,p));
        propertyService.createProperty(new Property("San Jose weekend less Property","North First","San Jose State","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,400,50,p));
        propertyService.createProperty(new Property("San Jose both greater Property","North First","San Jose State","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,400,500,p));
        propertyService.createProperty(new Property("San Jose weekend greater Property","North First","San Jose State","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,40,500,p));

        assetRepo.save(new Asset("33 S 3rd Street", "San Jose", "http://media.graytvinc.com/images/wcjb_apartment-living-room.jpg", 3));
        assetRepo.save(new Asset("101 San Fernando", "San Jose", "http://media.graytvinc.com/images/wcjb_apartment-living-room.jpg", 3));
        logger.info("Find All -> {}", assetRepo.findByOwner(3));



    }
}
