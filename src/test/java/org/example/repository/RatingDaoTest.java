package org.example.repository;

import org.example.model.Rating;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RatingDaoTest {
    private RatingDao ratingDao;
    private Rating r1;

    @Before
    public void SetUp(){
        ratingDao = new RatingDaoImpl();
        r1 = new Rating();
        r1.setRatingId(0);
        r1.setRatingScore(9);
        r1.setGoodReview("yummy");
        r1.setBadReview("expensive");
        r1=ratingDao.save(r1);
    }
    @After
    public void tearDown(){
        ratingDao.delete(r1);
    }

    @Test
    public void getRatingsTest(){
        List<Rating> ratings = ratingDao.getRatings();
        int expectedNumberOfUser = 1;
        assertEquals(expectedNumberOfUser, ratings.size());
    }
}
