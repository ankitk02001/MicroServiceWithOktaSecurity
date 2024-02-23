package com.ankit.controller;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.entity.User;
import com.ankit.service.UserService;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	//create User
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);//another way to return
	}
	int retryCount=1;
	//single user getting this api is talking to Rating and Hotel Services MicroServices also
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="RatingHotelBreaker",fallbackMethod = "ratingHotelFallback")//to enable circuit breaker
	//@Retry(name="ratingHotelService", fallbackMethod ="ratingHotelFallback")//in this we retry xtimes the request if not then fallback executed
	@RateLimiter(name="userRateLimiter",fallbackMethod ="ratingHotelFallback")//
	public ResponseEntity<User> getSingleUser(@PathVariable String  userId){
		log.info("RetryCount {} ",retryCount);
		retryCount++;
	   	User user = userService.getUser(userId);
	   	return ResponseEntity.ok(user);//second way to return
	}
	
	//creating fallback method it will only run when any service is down
	//return type of fallback method is same as api for which fallback is design
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){

		log.info("fallback method is executed because because service is down: "+ex.getMessage());
		System.err.println("fallback method is executed because because service is down: "+ex.getMessage());
		//setting object using @builder present in Lombok
		User user = User.builder().email("dummy@gmail.com")
				.name("Dummy")
				.about("fallback mehod is executed bcz Some Service is down")
				.userId("xxx")
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	//getting all the user
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return new ResponseEntity<List<User>>(allUser, HttpStatus.OK);//third Way to return
	}
}
