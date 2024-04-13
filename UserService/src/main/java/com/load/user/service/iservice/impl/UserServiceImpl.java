package com.load.user.service.iservice.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.load.user.service.entities.Hotel;
import com.load.user.service.entities.Rating;
import com.load.user.service.entities.User;
import com.load.user.service.exception.ResourceNotFoundException;
import com.load.user.service.external.services.HotelServices;
import com.load.user.service.external.services.RatingServices;
import com.load.user.service.iservice.UserService;
import com.load.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServices hotelServices;
    
    @Autowired
    private RatingServices ratingServices; // Inject the Feign client


    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {

        String uid = UUID.randomUUID().toString();
        user.setUserId(uid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

    	List<User> user= userRepository.findAll();
    	
    	
        return user;

    }

    @Override
    public User getUser(String userId) {
    	
    
            // Retrieve user from the repository
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on Server"));

            // Print user details
            System.out.println("Email: " + user.getEmail() + " Name: " + user.getName() + " Id: " + user.getUserId());

            // Build the URL for fetching ratings
            String url =ratingServices+"/api/v1/ratings/users/" + user.getUserId();
            System.out.println("URL: " + url);

            // Use Feign client to get ratings
            List<Rating> ratings = ratingServices.getRatings(user.getUserId());

         // Log the ratings
         System.out.println("Ratings: " + ratings);

         // Process each rating and retrieve hotel details
         List<Rating> ratingList = ratings.stream()
                 .map(rating -> {
                     Hotel hotel = hotelServices.getHotel(rating.getHotelId());
                     rating.setHotel(hotel);
                     return rating;
                 })
                 .collect(Collectors.toList());

         // Log the final rating list
         System.out.println("Rating List: " + ratingList);

         // Set the rating list to the user and return
         user.setRating(ratingList);
         return user;

       
    }

}
