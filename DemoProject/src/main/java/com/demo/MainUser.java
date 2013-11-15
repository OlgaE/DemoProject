package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.user.User;
import com.demo.user.service.UserService;

public class MainUser 
{
    public void init()
    {
      ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
      UserService userManager = (UserService) ctx.getBean("userServiceImpl");
      
      User user = new User();
      user.setSurname("johndoe");
      user.setName("John Doe");
      
      userManager.insertUser(user);
      
      System.out.println("User inserted!");
      
      user = userManager.getUser("johndoe");
      
      System.out.println("\nUser fetched by username!"
        + "\nId: " + user.getId()
        + "\nUsername: " + user.getSurname()
        + "\nName: " + user.getName());
      
      user = userManager.getUserById(user.getId());
      
      System.out.println("\nUser fetched by ID!"
        + "\nId: " + user.getId()
        + "\nUsername: " + user.getSurname()
        + "\nName: " + user.getName());
      
 /*     List<User> users = userManager.getUsers();
      
      System.out.println("\nUser list fetched!"
          + "\nUser count: " + users.size());*/

    }
}
