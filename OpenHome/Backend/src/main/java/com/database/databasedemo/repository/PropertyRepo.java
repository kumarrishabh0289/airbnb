package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Qualifier("property")
@Transactional
public interface PropertyRepo extends JpaRepository<Property,Integer> , JpaSpecificationExecutor {

    @Query("SELECT p FROM property p WHERE p.owner_id = ?1 AND p.status = 'Created'")
    List<Property> findByOwner(Person ownerId);

    List<Property> findByCityLike(String city);
}
