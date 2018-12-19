package com.rk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.UserBean;
import com.rk.model.User;
import com.rk.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new UserBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("useredit", null);
		model.addAttribute("users",  prepareListofBean(userService.listUsers()));
	}
	
	public boolean isAdmin(HttpSession session) {
		User user = UserBean.getAuthenticatedUser(session);
		return user.getRole().equals("ADMIN");
	}
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!isAdmin(session)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("user");
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  UserBean userBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!isAdmin(session)) {
			return new ModelAndView("redirect:/");
		}
		
		User user = UserBean.getAuthenticatedUser(session);
		if(user.getUsername().equals(userBean.getUsername())) {
			UserBean.setAuthenticatedUser(session, null);
		}
		try {
			userService.deleteUser(prepareModel(userBean));
		}
		catch(Exception e) {
			return new ModelAndView("user","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/user");
	}
	
//	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@Valid @ModelAttribute("command")  UserBean userBean,
//			BindingResult result, HttpSession session) {
//		if(!UserBean.isAuthenticated(session)) {
//			return new ModelAndView("redirect:/login");
//		}
//		if(!isAdmin(session)) {
//			return new ModelAndView("redirect:/");
//		}
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("useredit", prepareBean(userService.getUser(userBean.getUsername())));
//		model.put("action", "Edit");
//		return new ModelAndView("user", model);
//	}
	
	@RequestMapping(value = "/user/switchrole", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  UserBean userBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!isAdmin(session)) {
			return new ModelAndView("redirect:/");
		}
		
		userBean = prepareBean(userService.getUser(userBean.getUsername()));

		if(userBean.getRole().equals("ADMIN")) {
			userBean.setRole("STAFF");
		}
		else {
			userBean.setRole("ADMIN");
		}
		
		User user = UserBean.getAuthenticatedUser(session);
		if(user.getUsername().equals(userBean.getUsername())) {
			user.setRole(userBean.getRole());
			UserBean.setAuthenticatedUser(session, null);
		}
		
		userService.updateUser(prepareModel(userBean), false);
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") UserBean userBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!isAdmin(session)) {
			return new ModelAndView("redirect:/");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("user",model);
		}
		if(!UserBean.isValidPassword(userBean.getPassword())) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter a valid password.");
			return new ModelAndView("changepass",model);
		}
		if(!UserBean.isValidUsername(userBean.getUsername())) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter a valid username.");
			return new ModelAndView("changepass",model);
		}
		if(!userBean.getPassword().equals(userBean.getConfpassword())) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Passwords don't match.");
			return new ModelAndView("user",model);
		}
		if(userService.getUser(userBean.getUsername()) != null) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "User already exists.");
			return new ModelAndView("user",model);
		}
		
		User user = prepareModel(userBean);
		userService.addUser(user, true);
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/changepass", method = RequestMethod.GET)
	public ModelAndView changePassword(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("changepass");
	}
	
	@RequestMapping(value = "/user/changepass", method = RequestMethod.POST)
	public ModelAndView changePassword(HttpSession session,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confNewPassword") String confNewPassword) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		User user = UserBean.getAuthenticatedUser(session);
		Map<String, Object> model = new HashMap<String, Object>();
		if(!userService.isValid(user.getUsername(), oldPassword)) {
			model.put("errorMessage", "Current password entered is incorrect.");
			return new ModelAndView("changepass",model);
		}
		if(!newPassword.equals(confNewPassword)) {
			model.put("errorMessage", "Passwords don't match.");
			return new ModelAndView("changepass",model);
		}
		if(!UserBean.isValidPassword(newPassword)) {
			model.put("errorMessage", "Please enter a valid password.");
			return new ModelAndView("changepass",model);
		}
		user.setPassword(newPassword);
		userService.updateUser(user, true);
		UserBean.setAuthenticatedUser(session, userService.getUser(user.getUsername()));
		model.put("successMessage", "Password Changed Successfully.");
		return new ModelAndView("changepass",model);
	}
	
	private User prepareModel(UserBean userBean){
		User user = new User();
		user.setUsername(userBean.getUsername());
		user.setPassword(userBean.getPassword());
		user.setName(userBean.getName());
		user.setEmail(userBean.getEmail());
		user.setRole(userBean.getRole());
		return user;
	}
	
	private List<UserBean> prepareListofBean(List<User> users){
		List<UserBean> beans = null;
		if(users != null && !users.isEmpty()){
			beans = new ArrayList<UserBean>();
			for(User user : users){
				beans.add(prepareBean(user));
			}
		}
		return beans;
	}
	
	private UserBean prepareBean(User user){
		UserBean bean = new UserBean();
		bean.setUsername(user.getUsername());
		bean.setPassword(user.getPassword());
		bean.setName(user.getName());
		bean.setEmail(user.getEmail());
		bean.setRole(user.getRole());
		return bean;
	}
}
