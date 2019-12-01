package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Person;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonSpringDataRepo extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM person p WHERE p.name = ?1")
    Person findByName(String name);

    @Query("SELECT p FROM person p WHERE p.email = ?1")
    Person findByEmail(String email);
}
