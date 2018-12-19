package com.rk.bean;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class EmployeeBean {
	private Integer empID;

	@NotNull
	private String name;
	
	@NotNull
	private Integer salary;
	
	@NotNull
	private Date joinDate;
	
	@NotNull
	private Integer worksIn;
	private List<Long> contact;

	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	public Integer getWorksIn() {
		return worksIn;
	}
	public void setWorksIn(Integer worksIn) {
		this.worksIn = worksIn;
	}
	
	public List<Long> getContact() {
		return contact;
	}
	public void setContact(List<Long> contact) {
		this.contact = contact;
	}
}
