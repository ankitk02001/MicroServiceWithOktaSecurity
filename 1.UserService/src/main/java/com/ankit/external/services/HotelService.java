package com.ankit.external.services;

import com.ankit.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")//provide name which service you want to call
public interface HotelService {
   // no need to write public in interface bcz alrealy it always public by default in interface

    @GetMapping("/hotel/{hotelId}")//hotelId takes hotelId in path variable
     Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
