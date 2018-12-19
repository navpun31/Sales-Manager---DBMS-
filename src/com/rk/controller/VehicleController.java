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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.*;
import com.rk.model.*;
import com.rk.service.*;

@Controller
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private TransportAgentService transportAgentService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new VehicleBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("vehicle", null);
		model.addAttribute("vehicles",  prepareListofBean(vehicleService.listVehicles()));
		model.addAttribute("transportAgents", transportAgentService.listTransportAgents());
	}
	
	@RequestMapping(value="/vehicle", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("vehicle");
	}

	@RequestMapping(value = "/vehicle/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  VehicleBean vehicleBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			vehicleService.deleteVehicle(prepareModel(vehicleBean));
		}
		catch(Exception e) {
			return new ModelAndView("vehicle","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/vehicle");
	}
	
	@RequestMapping(value = "/vehicle/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  VehicleBean vehicleBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("vehicle", prepareBean(vehicleService.getVehicle(vehicleBean.getVehicleNo())));
		model.put("action", "Edit");
		return new ModelAndView("vehicle", model);
	}
	
	@RequestMapping(value = "/vehicle/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") VehicleBean vehicleBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("vehicle",model);
		}
		
		Vehicle vehicle = prepareModel(vehicleBean);
		vehicleService.addVehicle(vehicle);
		vehicleService.addContact(vehicle, vehicleBean.getContact());
		return new ModelAndView("redirect:/vehicle");
	}
	
	@RequestMapping(value = "/transportagent/vehicles/{agentID}", method = RequestMethod.GET)
	public ModelAndView agentParties(HttpSession session,
			@PathVariable("agentID") Integer agentID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("vehicles", prepareListofBean(vehicleService.listAgentVehicles(agentID)));
		return new ModelAndView("agentVehicles",model);
	}
	
	private Vehicle prepareModel(VehicleBean vehicleBean){
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleNo(vehicleBean.getVehicleNo());
		vehicle.setType(vehicleBean.getType());
		vehicle.setDriverName(vehicleBean.getDriverName());
		vehicle.setAgentID(vehicleBean.getAgentID());
		return vehicle;
	}
	
	private List<VehicleBean> prepareListofBean(List<Vehicle> vehicles){
		List<VehicleBean> beans = null;
		if(vehicles != null && !vehicles.isEmpty()){
			beans = new ArrayList<VehicleBean>();
			for(Vehicle vehicle : vehicles){
				beans.add(prepareBean(vehicle));
			}
		}
		return beans;
	}
	
	private VehicleBean prepareBean(Vehicle vehicle){
		VehicleBean bean = new VehicleBean();
		bean.setVehicleNo(vehicle.getVehicleNo());
		bean.setType(vehicle.getType());
		bean.setDriverName(vehicle.getDriverName());
		bean.setAgentID(vehicle.getAgentID());
		if(vehicle.getAgentID() != null) {
			bean.setTransportAgent(transportAgentService.getTransportAgent(vehicle.getAgentID()));
		}
		else {
			bean.setTransportAgent(null);
		}
		bean.setContact(vehicleService.getContact(vehicle));
		return bean;
	}
}
