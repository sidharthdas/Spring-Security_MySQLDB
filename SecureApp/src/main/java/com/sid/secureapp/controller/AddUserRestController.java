package com.sid.secureapp.controller;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sid.secureapp.model.User;

@RestController
@Transactional
public class AddUserRestController {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "workinng";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser() {
		User user1 = new User();
		User user2 = new User();
		
		user1.setUsername("sidharth");
		user1.setPassword("qwe");
		user2.setUsername("sagar");
		user2.setPassword("qwe");
		
		getSession().save(user1);
		getSession().save(user2);
		
		return "Added.";
	}

}
