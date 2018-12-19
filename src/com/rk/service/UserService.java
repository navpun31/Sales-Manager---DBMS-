package com.rk.service;

import java.util.List;

import com.rk.model.User;

public interface UserService {
	public void addUser(User user, boolean hashPass);
	public void updateUser(User user, boolean hashPass);
	public List<User> listUsers();
	public User getUser(String username);
	public void deleteUser(User user);	
	public boolean isValid(String username, String password);
	public boolean isAdmin(String username);
	public boolean isStaff(String username);
}
