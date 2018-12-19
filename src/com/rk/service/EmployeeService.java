package com.rk.service;

import java.util.List;

import com.rk.model.Employee;

public interface EmployeeService {
	public void addEmployee(Employee employee);
	public List<Employee> listEmployees();
	public List<Employee> listWarehouseEmployees(Integer warehouseID);
	public Employee getEmployee(Integer empID);
	public void deleteEmployee(Employee employee);
	public void addContact(Employee employee, List<Long> contact);
	public List<Long> getContact(Employee employee);
}
