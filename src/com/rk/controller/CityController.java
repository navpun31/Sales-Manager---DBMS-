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

import com.rk.bean.CityBean;
import com.rk.bean.UserBean;
import com.rk.model.City;
import com.rk.service.CityService;
import com.rk.service.StateService;

@Controller
public class CityController {
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new CityBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("city", null);
		model.addAttribute("cities",  prepareListofBean(cityService.listCities()));
		model.addAttribute("states",  stateService.listStates());
	}
	
	@RequestMapping(value="/city", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("city");
	}

	@RequestMapping(value = "/city/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  CityBean cityBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			cityService.deleteCity(prepareModel(cityBean));
		}
		catch(Exception e) {
			return new ModelAndView("city","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/city");
	}
	
	@RequestMapping(value = "/city/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  CityBean cityBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("city", prepareBean(cityService.getCity(cityBean.getCity())));
		model.put("action", "Edit");
		return new ModelAndView("city", model);
	}
	
	@RequestMapping(value = "/city/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") CityBean cityBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("city",model);
		}
		
		City city = prepareModel(cityBean);
		cityService.addCity(city);
		return new ModelAndView("redirect:/city");
	}
	
	private City prepareModel(CityBean cityBean){
		City city = new City();
		city.setCity(cityBean.getCity());
		city.setState(cityBean.getState());
		return city;
	}
	
	private List<CityBean> prepareListofBean(List<City> cities){
		List<CityBean> beans = null;
		if(cities != null && !cities.isEmpty()){
			beans = new ArrayList<CityBean>();
			for(City city : cities){
				beans.add(prepareBean(city));
			}
		}
		return beans;
	}
	
	private CityBean prepareBean(City city){
		CityBean bean = new CityBean();
		bean.setCity(city.getCity());
		bean.setState(city.getState());
		return bean;
	}
}
