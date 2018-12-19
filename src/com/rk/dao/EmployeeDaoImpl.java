package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployees() {
		return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listWarehouseEmployees(Integer warehouseID) {
		return (List<Employee>) sessionFactory.getCurrentSession().createQuery("From Employee where worksIn = " + warehouseID).list();
	}

	public Employee getEmployee(Integer empID) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empID);
	}

	public void deleteEmployee(Employee employee) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Employee WHERE empID = " + employee.getEmpID()).executeUpdate();
	}

	public void addContact(Employee employee, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM empContact WHERE empID = " + employee.getEmpID()).executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO empContact VALUES('" + employee.getEmpID() + "', " + L + ")").executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Long> getContact(Employee employee) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM empContact WHERE empID = " + employee.getEmpID()).list();
	}

}
