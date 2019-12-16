package com.database.databasedemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private int reviewId;

    @Column(name = "person_id", nullable = false)
    private int personId;

    @Column(name = "total_rating")
    private int totalRating;

    public Reviews(int personId, int totalRating, int reviewedByCount) {
        this.personId = personId;
        this.totalRating = totalRating;
        this.reviewedByCount = reviewedByCount;
    }

    @Column(name = "reviewed_by_count")
    private int reviewedByCount;

    public Reviews() {

    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getReviewedByCount() {
        return reviewedByCount;
    }

    public void setReviewedByCount(int reviewedByCount) {
        this.reviewedByCount = reviewedByCount;
    }
}


