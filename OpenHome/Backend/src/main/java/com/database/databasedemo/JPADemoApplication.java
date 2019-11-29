package com.database.databasedemo;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.repository.PersonSpringDataRepo;
import com.database.databasedemo.service.PropertyService;
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
        Property newProp=new Property("San Jose Property","North First","San Jose","California",95112,"https://photos.zillowstatic.com/p_e/ISynct0mwkakxh0000000000.jpg","Condo","Full",2,900,p);
        Property newProp1=new Property("Sunnyvale Property","North First","Sunnyvale","California",95112,"https://cdn.cnn.com/cnnnext/dam/assets/150511105029-airbnb-architecture--fox-island-full-169.jpg","Condo","Partial",2,900,p);
        logger.info("Insert  Property-> {}");

        propertyService.createProperty(newProp);
        propertyService.createProperty(newProp1);
        logger.info("Find property with property id 1{} ->",propertyService.getProperty(1).toString());
        logger.info("Find All -> {}", propertyService.getAllProperties());

        logger.info("Find properties for owner with id 5->{}",propertyService.getHostProperties(p));



        logger.info("Find By ID -> {}", repo.findById(2));
        repo.deleteById(2);



        assetRepo.save(new Asset("33 S 3rd Street", "San Jose", "http://media.graytvinc.com/images/wcjb_apartment-living-room.jpg", 3));
        assetRepo.save(new Asset("101 San Fernando", "San Jose", "http://media.graytvinc.com/images/wcjb_apartment-living-room.jpg", 3));
        logger.info("Find All -> {}", assetRepo.findByOwner(3));



    }
}
