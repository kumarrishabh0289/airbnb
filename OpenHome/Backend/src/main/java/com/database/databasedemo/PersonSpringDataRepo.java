package com.database.databasedemo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonSpringDataRepo extends JpaRepository<Person , Integer> {
    @Query("SELECT p FROM person p WHERE p.name = ?1")
    Person findByName(String name);
}
