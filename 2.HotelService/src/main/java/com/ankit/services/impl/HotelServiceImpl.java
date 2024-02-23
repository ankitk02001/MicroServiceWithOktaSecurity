package com.ankit.services.impl;

import com.ankit.entity.Hotel;
import com.ankit.entity.repository.HotelRepository;
import com.ankit.exception.ResourceNotFoundException;
import com.ankit.services.HotelServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor//for constructor injection
public class HotelServiceImpl implements HotelServices {

    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }
    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("hotel with given id not found!!")
        );
    }
}
