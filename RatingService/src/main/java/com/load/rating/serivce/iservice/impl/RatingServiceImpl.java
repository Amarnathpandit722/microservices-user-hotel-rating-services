package com.load.rating.serivce.iservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.load.rating.serivce.entities.Rating;
import com.load.rating.serivce.iservice.RatingService;
import com.load.rating.serivce.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
       return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();

        }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
       return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
       return ratingRepository.findByHotelId(hotelId);
    }
    
}
