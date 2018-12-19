package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {	
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	public User getUser(String username) {
		return (User) sessionFactory.getCurrentSession().get(User.class, username);
	}

	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM User WHERE username = '" + user.getUsername() + "'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public boolean isValid(String username, String password) {
		List<Object> rows = sessionFactory.getCurrentSession().createSQLQuery("SELECT username FROM User WHERE username = '" + username + "' and BINARY password = '" + password + "'").list();
		return !rows.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public boolean isAdmin(String username) {
		List<Object> rows = sessionFactory.getCurrentSession().createSQLQuery("SELECT username FROM User WHERE username = '" + username + "' and role = 'ADMIN'").list();
		return !rows.isEmpty();
	}
	
	public boolean isStaff(String username) {
		return !isAdmin(username);
	}
}
