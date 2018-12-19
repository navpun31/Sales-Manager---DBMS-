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

import com.rk.bean.EmployeeBean;
import com.rk.bean.UserBean;
import com.rk.model.Employee;
import com.rk.service.EmployeeService;
import com.rk.service.WarehouseService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WarehouseService warehouseService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new EmployeeBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("employee", null);
		model.addAttribute("employees",  prepareListofBean(employeeService.listEmployees()));
		model.addAttribute("warehouses",  warehouseService.listWarehouses());
	}
	
	@RequestMapping(value="/employee", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("employee");
	}

	@RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  EmployeeBean employeeBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			employeeService.deleteEmployee(prepareModel(employeeBean));
		}
		catch(Exception e) {
			return new ModelAndView("employee","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/employee");
	}
	
	@RequestMapping(value = "/employee/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  EmployeeBean employeeBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareBean(employeeService.getEmployee(employeeBean.getEmpID())));
		model.put("action", "Edit");
		return new ModelAndView("employee", model);
	}
	
	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") EmployeeBean employeeBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("employee",model);
		}
		
		Employee employee = prepareModel(employeeBean);
		if(employee.getEmpID() != null)
			warehouseService.setManagerNull(employee.getEmpID());
		employeeService.addEmployee(employee);
		employeeService.addContact(employee, employeeBean.getContact());
		return new ModelAndView("redirect:/employee");
	}
	
	@RequestMapping(value = "/warehouse/employees/{warehouseID}", method = RequestMethod.GET)
	public ModelAndView products(HttpSession session,
			@PathVariable("warehouseID") Integer warehouseID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("employeelistWarehouse", "employees", prepareListofBean(employeeService.listWarehouseEmployees(warehouseID)));
	}
	
	private Employee prepareModel(EmployeeBean employeeBean){
		Employee employee = new Employee();
		employee.setEmpID(employeeBean.getEmpID());
		employee.setSalary(employeeBean.getSalary());
		employee.setWorksIn(employeeBean.getWorksIn());
		employee.setJoinDate(employeeBean.getJoinDate());
		employee.setName(employeeBean.getName());
		return employee;
	}
	
	private List<EmployeeBean> prepareListofBean(List<Employee> employees){
		List<EmployeeBean> beans = null;
		if(employees != null && !employees.isEmpty()){
			beans = new ArrayList<EmployeeBean>();
			for(Employee employee : employees){
				beans.add(prepareBean(employee));
			}
		}
		return beans;
	}
	
	private EmployeeBean prepareBean(Employee employee){
		EmployeeBean bean = new EmployeeBean();
		bean.setEmpID(employee.getEmpID());
		bean.setSalary(employee.getSalary());
		bean.setWorksIn(employee.getWorksIn());
		bean.setJoinDate(employee.getJoinDate());
		bean.setName(employee.getName());
		bean.setContact(employeeService.getContact(employee));
		return bean;
	}
}
