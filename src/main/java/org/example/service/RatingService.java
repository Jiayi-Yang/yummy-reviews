package org.example.service;

import org.example.model.Rating;
import org.example.repository.RatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingDao ratingDao;

    public Rating save(Rating rating){
        return ratingDao.save(rating);
    }
    public Rating update(Rating rating){
        return ratingDao.update(rating);
    }
    public boolean delete(Rating rating){
        return ratingDao.delete(rating);
    }
    public List<Rating> getRatings(){
        return ratingDao.getRatings();
    }
}
