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
public class WarehouseController {
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new WarehouseBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("warehouse", null);
		model.addAttribute("warehouses",  prepareListofBean(warehouseService.listWarehouses()));
		model.addAttribute("cities", cityService.listCities());
		model.addAttribute("categories", categoryService.listCategories());
		model.addAttribute("employees", null);
	}
	
	@RequestMapping(value="/warehouse", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("warehouse");
	}

	@RequestMapping(value = "/warehouse/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  WarehouseBean warehouseBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			warehouseService.deleteWarehouse(prepareModel(warehouseBean));
		}
		catch(Exception e) {
			return new ModelAndView("warehouse","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/warehouse");
	}
	
	@RequestMapping(value = "/warehouse/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  WarehouseBean warehouseBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("warehouse", prepareBean(warehouseService.getWarehouse(warehouseBean.getWarehouseID())));
		model.put("employees", employeeService.listWarehouseEmployees(warehouseBean.getWarehouseID()));
		model.put("action", "Edit");
		return new ModelAndView("warehouse", model);
	}
	
	@RequestMapping(value = "/warehouse/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") WarehouseBean warehouseBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("warehouse",model);
		}
		if(warehouseBean.getManagerID() == -1) {
			warehouseBean.setManagerID(null);
		}
		if(warehouseBean.getManagerID() != null) {
			Employee manager = employeeService.getEmployee(warehouseBean.getManagerID());
			if(!manager.getWorksIn().equals(warehouseBean.getWarehouseID())) {
				System.out.println(manager.getWorksIn());
				System.out.println(warehouseBean.getWarehouseID());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("errorMessage", "Please enter valid details.");
				return new ModelAndView("warehouse",model);
			}
		}
		
		Warehouse warehouse = prepareModel(warehouseBean);
		warehouseService.addWarehouse(warehouse);
		warehouseService.addContact(warehouse, warehouseBean.getContact());
		return new ModelAndView("redirect:/warehouse");
	}
	
	private Warehouse prepareModel(WarehouseBean warehouseBean){
		Warehouse warehouse = new Warehouse();
		warehouse.setWarehouseID(warehouseBean.getWarehouseID());
		warehouse.setCategoryHSN(warehouseBean.getCategoryHSN());
		warehouse.setManagerID(warehouseBean.getManagerID());
		if(warehouseBean.getAddress() != null) {
			warehouse.setAddressLine1(warehouseBean.getAddress().getAddressLine1());
			warehouse.setAddressLine2(warehouseBean.getAddress().getAddressLine2());
			warehouse.setPin(warehouseBean.getAddress().getPin());
			warehouse.setCity(warehouseBean.getAddress().getCity());
		}
		return warehouse;
	}
	
	private List<WarehouseBean> prepareListofBean(List<Warehouse> warehouses){
		List<WarehouseBean> beans = null;
		if(warehouses != null && !warehouses.isEmpty()){
			beans = new ArrayList<WarehouseBean>();
			for(Warehouse warehouse : warehouses){
				beans.add(prepareBean(warehouse));
			}
		}
		return beans;
	}
	
	private WarehouseBean prepareBean(Warehouse warehouse){
		WarehouseBean bean = new WarehouseBean();
		bean.setWarehouseID(warehouse.getWarehouseID());
		bean.setCategoryHSN(warehouse.getCategoryHSN());
		bean.setManagerID(warehouse.getManagerID());
		
		Address address = new Address();
		address.setAddressLine1(warehouse.getAddressLine1());
		address.setAddressLine2(warehouse.getAddressLine2());
		address.setPin(warehouse.getPin());
		address.setCity(warehouse.getCity());
		City city = cityService.getCity(warehouse.getCity());
		State state = stateService.getState(city.getState());
		address.setStateName(state.getState());
		address.setStateCode(state.getStateCode());
		address.setCountry(state.getCountry());
		
		if(warehouse.getManagerID() != null) {
			bean.setManager(employeeService.getEmployee(warehouse.getManagerID()));
		}
		else {
			bean.setManager(null);
		}
		
		if(warehouse.getCategoryHSN() != null) {
			bean.setCategory(categoryService.getCategory(warehouse.getCategoryHSN()));
		}
		else {
			bean.setCategory(null);
		}
		
		bean.setAddress(address);
		bean.setContact(warehouseService.getContact(warehouse));
		return bean;
	}
}
