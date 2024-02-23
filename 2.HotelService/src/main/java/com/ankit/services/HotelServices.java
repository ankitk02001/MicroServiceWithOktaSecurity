package com.ankit.services;

import com.ankit.entity.Hotel;

import java.util.List;

public interface HotelServices {

    //create
    Hotel  create(Hotel hotel);

    //getall
    List<Hotel> getAll();

    //get Single data
    Hotel getHotelById(String id);
}
