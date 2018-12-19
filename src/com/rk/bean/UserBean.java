package com.rk.bean;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.rk.model.User;

public class UserBean {
	@NotNull
	private String username;

	@NotNull
	@Size(min=8,max=20)
	private String password;
	private String confpassword;

	@NotNull
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	private String role;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfpassword() {
		return confpassword;
	}
	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public static boolean isAuthenticated(HttpSession session) {
		return (User) session.getAttribute("user") != null;
	}
	public static User getAuthenticatedUser(HttpSession session) {
		return (User) session.getAttribute("user");
	}
	public static void setAuthenticatedUser(HttpSession session, User user) {
		session.setAttribute("user",user);
	}
	public static boolean isValidUsername(String username) {
		return true;
	}
	public static boolean isValidPassword(String password) {
		return (password.length() >= 8 && password.length() <= 20);
	}
}
