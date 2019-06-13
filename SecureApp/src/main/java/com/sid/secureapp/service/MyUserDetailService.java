
package com.sid.secureapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sid.secureapp.model.User;

@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession(); 
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user = getSession().createQuery("FROM User where userName = :userName")
				.setParameter("userName", username).list();
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User 404");
		}
		return new UserPrincipal(user.get(0));
	}

}
