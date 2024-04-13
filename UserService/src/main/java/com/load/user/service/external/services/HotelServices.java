package com.load.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.load.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE",url="http://localhost:8082")
public interface HotelServices {

    @GetMapping("/api/v1/hotels/{hotelId}")
     Hotel getHotel( @PathVariable("hotelId") String hotelId);
    
     
     

}
