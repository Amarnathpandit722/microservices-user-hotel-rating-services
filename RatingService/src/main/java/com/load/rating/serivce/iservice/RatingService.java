package com.load.rating.serivce.iservice;

import java.util.List;

import com.load.rating.serivce.entities.Rating;

public interface RatingService {
    
    Rating createRating(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
