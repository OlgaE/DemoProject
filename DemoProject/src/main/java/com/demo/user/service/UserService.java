package com.demo.user.service;

import com.demo.user.User;

public interface UserService {

	  void insertUser(User user);
		
	  User getUserById(int userId);
		
	  User getUser(String username);
		
	  /* List<User> getUsers();*/
	}
