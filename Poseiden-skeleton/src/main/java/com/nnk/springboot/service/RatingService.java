package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatings(){
        return ratingRepository.findAll();
    }

    public  Rating getRating(Long id) throws NoSuchElementException {
        return ratingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Rating " + id + " does not exist"));
    }

    public void setRating(Rating rating){
        ratingRepository.save(rating);
    }

    public void updateRating(Long id, Rating rating) throws  NoSuchElementException{
        Rating oldRating = ratingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Rating " + id + " does not exist"));
        rating = rating.withId(oldRating.getId());
        ratingRepository.save(rating);
    }

    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }

}
