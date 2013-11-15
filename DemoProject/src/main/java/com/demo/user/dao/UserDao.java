package com.demo.user.dao;

import com.demo.user.User;

public interface UserDao {

	  void insertUser(User user);
	  
	  User getUserById(int userId);
	  
	  User getUser(String username);
	  
	  /*List<User> getUsers()*/;
	}
