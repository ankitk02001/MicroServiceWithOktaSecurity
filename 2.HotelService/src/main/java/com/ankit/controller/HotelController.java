package com.ankit.controller;

import com.ankit.entity.Hotel;
import com.ankit.services.HotelServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor//for constructor injection
public class HotelController {

    private HotelServices hotelServices;
    //create
    @PostMapping
    public ResponseEntity <Hotel>  save(@RequestBody Hotel hotel){
        Hotel createdUser = hotelServices.create(hotel);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    //get Single data
    //only internal or Admin scope user can access this
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('SCOPE_Admin') ")//only internal scope user can access this
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getdata(@PathVariable String id){
        Hotel hotelById = hotelServices.getHotelById(id);
        return new ResponseEntity<>(hotelById, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAlldata(){
        List<Hotel> all = hotelServices.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

}
