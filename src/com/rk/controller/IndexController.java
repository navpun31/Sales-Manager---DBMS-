package com.rk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.Address;
import com.rk.bean.ClientBean;
import com.rk.bean.UserBean;
import com.rk.model.City;
import com.rk.model.Client;
import com.rk.model.State;
import com.rk.service.CityService;
import com.rk.service.ClientService;
import com.rk.service.StateService;
import com.rk.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	
	@RequestMapping(value={"/","/index","/home","/welcome"}, method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpSession session) {	
		if(UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpSession session, 
			@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		if(!userService.isValid(username, password)) {
			UserBean.setAuthenticatedUser(session, null);
			return new ModelAndView("login","errorMessage","Invalid Credentials!");
		}
		UserBean.setAuthenticatedUser(session, userService.getUser(username));
		session.setAttribute("client", prepareBean(clientService.getClient()));
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		UserBean.setAuthenticatedUser(session, null);
		return new ModelAndView("redirect:/");
	}
	
	private ClientBean prepareBean(Client client){
		ClientBean bean = new ClientBean();
		
		Address address = new Address();
		address.setAddressLine1(client.getAddressLine1());
		address.setAddressLine2(client.getAddressLine2());
		address.setPin(client.getPin());
		address.setCity(client.getCity());
		City city = cityService.getCity(client.getCity());
		State state = stateService.getState(city.getState());
		address.setStateName(state.getState());
		address.setStateCode(state.getStateCode());
		address.setCountry(state.getCountry());
		
		bean.setAddress(address);
		bean.setBankAccountNo(client.getBankAccountNo());
		bean.setBankBranch(client.getBankBranch());
		bean.setBankName(client.getBankName());
		bean.setEmail(client.getEmail());
		bean.setFirmName(client.getFirmName());
		bean.setGstin(client.getGstin());
		bean.setId(client.getId());
		bean.setIfsc(client.getIfsc());
		bean.setLandline(client.getLandline());
		bean.setMobile(client.getMobile());
		return bean;
	}
}
