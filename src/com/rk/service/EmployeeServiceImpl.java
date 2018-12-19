package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.EmployeeDao;
import com.rk.model.Employee;

@Service("employeeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}

	public List<Employee> listEmployees() {
		return employeeDao.listEmployees();
	}

	public List<Employee> listWarehouseEmployees(Integer warehouseID) {
		return employeeDao.listWarehouseEmployees(warehouseID);
	}

	public Employee getEmployee(Integer empID) {
		return employeeDao.getEmployee(empID);
	}

	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);
	}

	public void addContact(Employee employee, List<Long> contact) {
		employeeDao.addContact(employee, contact);
	}

	public List<Long> getContact(Employee employee) {
		return employeeDao.getContact(employee);
	}
}
