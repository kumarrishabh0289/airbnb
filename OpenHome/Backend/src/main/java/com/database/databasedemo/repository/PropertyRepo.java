package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property,Integer> {
    @Query("SELECT p FROM property p WHERE p.owner_id = ?1")
    List<Property> findByOwner(Person ownerId);

}
