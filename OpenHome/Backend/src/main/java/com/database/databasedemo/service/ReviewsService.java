package com.database.databasedemo.service;

import com.database.databasedemo.entity.Person;
import com.database.databasedemo.entity.Property;
import com.database.databasedemo.entity.Reservations;
import com.database.databasedemo.entity.Reviews;
import com.database.databasedemo.mail.SendMail;
import com.database.databasedemo.repository.PersonJPARepo;
import com.database.databasedemo.repository.PropertyRepo;
import com.database.databasedemo.repository.ReservationRepo;
import com.database.databasedemo.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewsService {

    @Autowired
    ReviewRepo reviewRepo;


    public float getAverageReviews(int id) {

        Reviews review=reviewRepo.findByPersonId(id);
        if(review==null){
            return 0;
        }
        else{
            float avg=(float)review.getTotalRating()/(float)review.getReviewedByCount();
            return avg;
        }
    }

    public void addReview(int id,int rating) {

        Reviews review=reviewRepo.findByPersonId(id);
        if(review==null){
            review=new Reviews(id,rating,1);

           reviewRepo.save(review);
        }
        else{
            review.setTotalRating(review.getTotalRating()+rating);
            review.setReviewedByCount(review.getReviewedByCount()+1);
        }
    }

   }
