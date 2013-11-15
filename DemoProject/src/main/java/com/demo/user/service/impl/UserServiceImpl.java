package com.demo.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.user.dao.UserDao;
import com.demo.user.User;
import com.demo.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDAO;
  
  @Transactional
  public void insertUser(User user) {
    userDAO.insertUser(user);
  }

  @Transactional
  public User getUserById(int userId) {
    return userDAO.getUserById(userId);
  }
  
 @Transactional
  public User getUser(String username) {
    return userDAO.getUser(username);
  }

  /* @Transactional
  public List<User> getUsers() {
    return userDAO.getUsers();
  }*/

}