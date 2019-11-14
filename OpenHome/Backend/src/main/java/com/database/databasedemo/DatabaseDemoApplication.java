package com.database.databasedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
//public class DatabaseDemoApplication implements CommandLineRunner {
//	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	PersonJdbcDAO dao;
//	public static void main(String[] args) {
//		SpringApplication.run(DatabaseDemoApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		logger.info("All User -> {}",dao.findAll());
//
//	}
//}
