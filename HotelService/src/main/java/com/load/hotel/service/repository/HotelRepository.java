package com.load.hotel.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.load.hotel.service.entities.Hotels;

public interface HotelRepository  extends JpaRepository<Hotels, String> {

    
}
