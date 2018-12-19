package com.rk.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	@Id		
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "empID")
	private Integer empID;

	@Column(name = "name")
	private String name;
	
	@Column(name = "salary")
	private Integer salary;

	@Column(name = "joinDate")
	private Date joinDate;
	
	@Column(name = "worksIn")
	private Integer worksIn;

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
}
