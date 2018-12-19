package com.rk.controller;

import java.util.HashMap;
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

@Controller
public class ClientController {
	@Autowired
	private ClientService clientService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", null);
		model.addAttribute("command", new ClientBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("cities", cityService.listCities());
	}
	
	@RequestMapping(value="/client", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("client");
	}

	@RequestMapping(value = "/client/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") ClientBean clientBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("client",model);
		}
		
		Client client = prepareModel(clientBean);
		clientService.addClient(client);
		session.setAttribute("client", prepareBean(client));
		return new ModelAndView("client","successMessage","Saved Successfully.");
	}
	
	private Client prepareModel(ClientBean clientBean){
		Client client = new Client();
		client.setAddressLine1(clientBean.getAddressLine1());
		client.setAddressLine2(clientBean.getAddressLine2());
		client.setBankAccountNo(clientBean.getBankAccountNo());
		client.setBankBranch(clientBean.getBankBranch());
		client.setBankName(clientBean.getBankName());
		client.setCity(clientBean.getCity());
		client.setEmail(clientBean.getEmail());
		client.setFirmName(clientBean.getFirmName());
		client.setGstin(clientBean.getGstin());
		client.setId(clientBean.getId());
		client.setIfsc(clientBean.getIfsc());
		client.setLandline(clientBean.getLandline());
		client.setMobile(clientBean.getMobile());
		client.setPin(clientBean.getPin());
		return client;
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
