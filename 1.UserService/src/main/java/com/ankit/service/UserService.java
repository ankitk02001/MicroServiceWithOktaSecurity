package com.ankit.service;

import java.util.List;

import com.ankit.entity.User;

public interface UserService {

	
	//create user
	User saveUser(User user);
	
	//get AllUser
	List<User> getAllUser();
	
	//get User by Id
	User getUser(String userId);
	
	//todo: delete And Update by ur self
}
