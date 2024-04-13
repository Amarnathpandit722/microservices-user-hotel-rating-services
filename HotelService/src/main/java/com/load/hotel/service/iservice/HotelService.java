package com.load.hotel.service.iservice;

import java.util.List;

import com.load.hotel.service.entities.Hotels;

public interface HotelService {
    
    Hotels createHotels(Hotels hotels);

    List<Hotels> getAll();

    Hotels get(String Id);
    
}
