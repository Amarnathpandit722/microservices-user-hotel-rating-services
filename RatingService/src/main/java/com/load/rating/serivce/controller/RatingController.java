package com.load.rating.serivce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.load.rating.serivce.entities.Rating;
import com.load.rating.serivce.iservice.RatingService;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


     @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/add")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating2 = ratingService.createRating(rating);
        return new ResponseEntity<Rating>(rating2, HttpStatus.CREATED);
    }
  
        @GetMapping("/all")
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }
       // @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin') || hasAuthority('ROLE_USER')")
        @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsUserId(@PathVariable  String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
   @PreAuthorize("hasAuthority('Admin')")
        @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }





    
}
