package com.ankit.controller;

import com.ankit.entity.Rating;
import com.ankit.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor//for Costructor injection
public class RatingController {

    private RatingService ratingService;
    //creating rating
    @PostMapping
    public ResponseEntity<Rating> savedRating(@RequestBody Rating rating){
        Rating savedRating = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }
    //getAll
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
    }

    //get all rating by userId
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('SCOPE_Admin') ")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all of hotels by hotelId
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
