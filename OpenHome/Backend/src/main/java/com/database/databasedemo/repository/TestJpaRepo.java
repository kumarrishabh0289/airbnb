package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Test;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestJpaRepo extends JpaRepository<Test, Integer> {

}
