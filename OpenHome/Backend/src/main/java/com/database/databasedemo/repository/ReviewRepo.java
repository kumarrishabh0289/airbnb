package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Reviews;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("reviews")
@Transactional
public interface ReviewRepo extends JpaRepository<Reviews,Integer> , JpaSpecificationExecutor {

    public Reviews findByPersonId(int id);

}
