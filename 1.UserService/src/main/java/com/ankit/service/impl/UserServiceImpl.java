package com.ankit.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ankit.entity.Hotel;
import com.ankit.entity.Rating;
import com.ankit.external.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ankit.entity.User;
import com.ankit.exception.ResourceNotFounException;
import com.ankit.repository.UserRepository;
import com.ankit.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor//for constructor injection
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RestTemplate restTemplate;//for taking data from another microservices
	private HotelService hotelService;//feing clien interface
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();//generate unique Id used in real time project
	     user.setUserId(randomUserId);
		return userRepository.save(user);
	}
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// get the user from userRepository
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFounException("user with given id is not found on server" + userId));
		//fetching ratingByUserId from the  RATING-SERVICE
		String url="http://RATING-SERVICE/ratings/users/"+user.getUserId();
		//localhost:8083=RATING-SERVICE REPLACED BY
		Rating[] ratingsUser = restTemplate.getForObject(url, Rating[].class);
         log.info("{} ",ratingsUser);//printing value on console using logger from SLF4j
		System.err.println(ratingsUser);//printing value on console from  RATING-SERVICE

		List<Rating> ratings = Arrays.stream(ratingsUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			//api call to HOTEL-SERVICE to get the Hotel localhost:8082=HOTEL-SERVICE
			//  String url1="http://HOTEL-SERVICE/hotel/"+rating.getHotelId();
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(url1, Hotel.class);
			Hotel hotel =hotelService.getHotel(rating.getHotelId());               //forEntity.getBody();
			//log.info("Response Status code:{} ",forEntity.getStatusCode());
			//set the hotel to rating
			rating.setHotel(hotel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);//also setting the rating to the user

		return user;
	}

}
