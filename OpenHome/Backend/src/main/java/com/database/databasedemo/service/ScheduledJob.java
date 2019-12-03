package com.database.databasedemo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ScheduledJob {
        @Autowired
        TimeService timeService;
        private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        @Scheduled(fixedRate = 5000)
        public void processAutomatedJobs() {
            //log.info("The time is now {}", dateFormat.format(new Date()));
           // log.info("The time form time service {}", timeService.getCurrentTime().getHour());
            if(timeService.getCurrentTime().getHour()==3){
                System.out.println("Check for no-show");
            }
            if(timeService.getCurrentTime().getHour()==11){
                System.out.println("Automatic check-out");
            }

        }

}
