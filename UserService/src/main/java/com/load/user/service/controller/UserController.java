package com.load.user.service.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.load.user.service.entities.User;
import com.load.user.service.iservice.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

     private org.slf4j.Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return new ResponseEntity<User>(user1, HttpStatus.CREATED);
    }
  int retryCount=1;
    
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService" ,fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
    	 logger.info(userId);
        User user = userService.getUser(userId);
          //retryCount++;
         // logger.info(" retryCount ",retryCount );
         
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }
   @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> uList = userService.getAllUser();
        return new ResponseEntity<List<User>>(uList, HttpStatus.OK);

    }
    
  
//creating fall backy method
public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
    //logger.info("Fallback is exceuted because service is down ");
  ex.printStackTrace();
   User user = User.builder();
   return new ResponseEntity<User>(user, HttpStatus.OK);
    

}


}



