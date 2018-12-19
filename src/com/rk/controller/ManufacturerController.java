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
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.*;
import com.rk.model.*;
import com.rk.service.*;

@Controller
public class ManufacturerController {
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new ManufacturerBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("manufacturer", null);
		model.addAttribute("manufacturers",  prepareListofBean(manufacturerService.listManufacturers()));
		model.addAttribute("cities", cityService.listCities());
	}
	
	@RequestMapping(value="/manufacturer", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("manufacturer");
	}

	@RequestMapping(value = "/manufacturer/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  ManufacturerBean manufacturerBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			manufacturerService.deleteManufacturer(prepareModel(manufacturerBean));
		}
		catch(Exception e) {
			return new ModelAndView("manufacturer","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/manufacturer");
	}
	
	@RequestMapping(value = "/manufacturer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  ManufacturerBean manufacturerBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("manufacturer", prepareBean(manufacturerService.getManufacturer(manufacturerBean.getManuID())));
		model.put("action", "Edit");
		return new ModelAndView("manufacturer", model);
	}
	
	@RequestMapping(value = "/manufacturer/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") ManufacturerBean manufacturerBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("manufacturer",model);
		}
		
		Manufacturer manufacturer = prepareModel(manufacturerBean);
		manufacturerService.addManufacturer(manufacturer);
		manufacturerService.addContact(manufacturer, manufacturerBean.getContact());
		return new ModelAndView("redirect:/manufacturer");
	}
	
	private Manufacturer prepareModel(ManufacturerBean manufacturerBean){
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setManuID(manufacturerBean.getManuID());
		manufacturer.setName(manufacturerBean.getName());
		if(manufacturerBean.getAddress() != null) {
			manufacturer.setAddressLine1(manufacturerBean.getAddress().getAddressLine1());
			manufacturer.setAddressLine2(manufacturerBean.getAddress().getAddressLine2());
			manufacturer.setPin(manufacturerBean.getAddress().getPin());
			manufacturer.setCity(manufacturerBean.getAddress().getCity());
		}
		return manufacturer;
	}
	
	private List<ManufacturerBean> prepareListofBean(List<Manufacturer> manufacturers){
		List<ManufacturerBean> beans = null;
		if(manufacturers != null && !manufacturers.isEmpty()){
			beans = new ArrayList<ManufacturerBean>();
			for(Manufacturer manufacturer : manufacturers){
				beans.add(prepareBean(manufacturer));
			}
		}
		return beans;
	}
	
	private ManufacturerBean prepareBean(Manufacturer manufacturer){
		ManufacturerBean bean = new ManufacturerBean();
		bean.setManuID(manufacturer.getManuID());
		bean.setName(manufacturer.getName());
		
		Address address = new Address();
		address.setAddressLine1(manufacturer.getAddressLine1());
		address.setAddressLine2(manufacturer.getAddressLine2());
		address.setPin(manufacturer.getPin());
		address.setCity(manufacturer.getCity());
		City city = cityService.getCity(manufacturer.getCity());
		State state = stateService.getState(city.getState());
		address.setStateName(state.getState());
		address.setStateCode(state.getStateCode());
		address.setCountry(state.getCountry());
		bean.setAddress(address);
		
		bean.setContact(manufacturerService.getContact(manufacturer));
		return bean;
	}
}
