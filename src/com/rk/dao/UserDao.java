package com.rk.dao;

import java.util.List;

import com.rk.model.User;

public interface UserDao {
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUsers();
	public User getUser(String username);
	public void deleteUser(User user);
	public boolean isValid(String username, String password);
	public boolean isAdmin(String username);
	public boolean isStaff(String username);
}
