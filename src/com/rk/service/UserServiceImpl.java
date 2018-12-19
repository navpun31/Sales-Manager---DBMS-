package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.bean.AES;
import com.rk.dao.UserDao;
import com.rk.model.User;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	private String hash(String s) {
		final String secretKey = "@**#ArKaYsOn#**@";
//		admin
//		2KRMrVaYXqmmPEWXuC47KA==
		return AES.encrypt(s, secretKey);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user, boolean hashPass) {
		if(hashPass) {
			user.setPassword(hash(user.getPassword()));
		}
		userDao.addUser(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(User user, boolean hashPass) {
		if(hashPass) {
			user.setPassword(hash(user.getPassword()));
		}
		userDao.updateUser(user);
	}

	public List<User> listUsers() {
		return userDao.listUsers();
	}

	public User getUser(String username) {
		return userDao.getUser(username);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public boolean isValid(String username, String password) {
		return userDao.isValid(username, hash(password));
	}

	public boolean isAdmin(String username) {
		return userDao.isAdmin(username);
	}
	
	public boolean isStaff(String username) {
		return userDao.isStaff(username);
	}
}
