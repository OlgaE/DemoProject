package com.demo.user.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.user.dao.UserDao;
import com.demo.user.User;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public User getUserById(int userId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	public User getUser(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		query.setParameter("username", username);
		return (User) query.list().get(0);
	}

/*	public List<User> getUsers() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from User where username = :username");
	    query.setParameter("username", username);
		return (List<User>) query.list().get(0);
	} */
}