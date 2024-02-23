package com.ankit.runner;

import com.ankit.entity.Rating;
import com.ankit.external.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRatingClass {
    //this class is to send post request for creating rating in RATING-SERVICE
    @Autowired
    private RatingService ratingService;

  //creating rating in RATING-SERVICE BY SENDING POST REQUEST
     public void createRating(){
         Rating rating = Rating.builder().rating(10).userId("78541d60-c9a5-4005-a3e4-ce3434551593")
                 .hotelId("c2e8eb8a-2cd5-4019-83f6-a1d8d4079ace").feedback("this is created by FeignClient").build();
         Rating savedRating = ratingService.createRating(rating);
         System.out.println("new rating creating: "+savedRating);

     }
}
