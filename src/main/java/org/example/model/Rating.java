package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    public Rating (){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private long ratingId;
    @Column(name = "rating_score")
    private int ratingScore;
    @Column(name = "good_reviews")
    private String goodReview;
    @Column(name = "bad_reviews")
    private String badReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public long getRatingId() {
        return ratingId+super.hashCode();
    }

    public void setRatingId (long ratingId){
        this.ratingId = ratingId;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore (int score){
        this.ratingScore = score;
    }

    public String getGoodReview() {
        return goodReview;
    }

    public void setGoodReview(String reviews){
        this.goodReview = reviews;
    }

    public String getBadReview() {
        return badReview;
    }

    public void setBadReview(String review) {
        this.badReview = review;
    }

    public void setUser(User user){
        this.user = user;
    }
}
