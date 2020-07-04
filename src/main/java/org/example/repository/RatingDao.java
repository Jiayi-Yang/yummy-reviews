package org.example.repository;

import org.example.model.Rating;

import java.util.List;

public interface RatingDao{
    Rating save(Rating rating);
    Rating update(Rating rating);
    boolean delete(Rating rating);
    List<Rating> getRatings();
    Rating getBy(Long id);
}