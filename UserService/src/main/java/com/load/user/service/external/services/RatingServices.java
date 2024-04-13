package com.load.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.load.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE" , url="http://localhost:8083")
public interface RatingServices {

	
	@GetMapping("/api/v1/ratings/users/{userId}")
	List<Rating> getRatings(@PathVariable("userId") String userId);
	
}
