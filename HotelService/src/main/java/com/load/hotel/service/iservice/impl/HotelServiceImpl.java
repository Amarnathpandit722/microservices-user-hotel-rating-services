package com.load.hotel.service.iservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.load.hotel.service.entities.Hotels;
import com.load.hotel.service.exception.ResourceNotFoundException;
import com.load.hotel.service.iservice.HotelService;
import com.load.hotel.service.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;



    @Override
    public Hotels createHotels(Hotels hotels) {
       
        String random = UUID.randomUUID().toString();
        hotels.setId(random);
        return hotelRepository.save(hotels);


    }

    @Override
    public List<Hotels> getAll() {
       
        return hotelRepository.findAll();



    }

    @Override
    public Hotels get(String Id) {
        return hotelRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Resource id is not found"));
        
    }
    
}


//.orElseThrow(()-> new ResourceNotFoundException("Hotel Id is not Foudn with given Id"))