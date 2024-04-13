package com.load.hotel.service.controller;

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

import com.load.hotel.service.entities.Hotels;
import com.load.hotel.service.iservice.HotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

//    @PreAuthorize("hasAuthority('Admin')")
        @PostMapping("/add")
        public ResponseEntity<Hotels> createHotels(@RequestBody Hotels hotels){
            return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotels(hotels));
        }
    // @PreAuthorize("hasAuthority('Admin')|| hasAuthority('ROLE_USER')")
        @GetMapping("/{hotelId}")
        public ResponseEntity<Hotels> getHotels(@PathVariable String  hotelId){
            return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
        }
       
        @GetMapping("/all")
        public ResponseEntity<List<Hotels>> getAllHotels(){
            return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
        }
        
        
        
        







    
}
