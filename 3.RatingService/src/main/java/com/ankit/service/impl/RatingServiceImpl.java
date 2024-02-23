package com.ankit.service.impl;

import com.ankit.entity.Rating;
import com.ankit.repository.RatingRepository;
import com.ankit.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor//for constructor
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        String id = UUID.randomUUID().toString();
        rating.setRatingId(id);
        Rating savedRating = ratingRepository.save(rating);
        return savedRating;
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
