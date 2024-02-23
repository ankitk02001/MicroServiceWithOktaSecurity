package com.ankit.external.services;

import com.ankit.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    //get


    //post
    @PostMapping("/ratings")//creating record
    public Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")//updating record
    public Rating updateRating(@PathVariable String ratingId , Rating rating);

    //for delete
    @DeleteMapping("/ratings/{ratingId}")//for deleting record in RATING-SERVICE
    public void deleteRating(@PathVariable String ratingId);
}
