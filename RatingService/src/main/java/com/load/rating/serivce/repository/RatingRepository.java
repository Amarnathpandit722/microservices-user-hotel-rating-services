package com.load.rating.serivce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.load.rating.serivce.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating,String> {
    // custom finder methods
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);

    // List<Rating> findHotelIdByUserId(String userId)


    
}
