package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.rk.model.Category;
import com.rk.model.Employee;

public class WarehouseBean {
	private Integer warehouseID;
	
	@NotNull
	private Integer categoryHSN;
	private Category category;

	private Integer managerID;
	private Employee manager;
	
	@NotNull
	private Address address;
	private List<Long> contact;
	
	public Integer getWarehouseID() {
		return warehouseID;
	}
	public void setWarehouseID(Integer warehouseID) {
		this.warehouseID = warehouseID;
	}

	public Integer getCategoryHSN() {
		return categoryHSN;
	}
	public void setCategoryHSN(Integer categoryHSN) {
		this.categoryHSN = categoryHSN;
	}

	public Integer getManagerID() {
		return managerID;
	}
	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getAddressLine1() {
		return this.address.getAddressLine1();
	}
	public void setAddressLine1(String addressLine1) {
		this.address.setAddressLine1(addressLine1);
	}
	public String getAddressLine2() {
		return this.address.getAddressLine2();
	}
	public void setAddressLine2(String addressLine2) {
		this.address.setAddressLine2(addressLine2);
	}
	public Integer getPin() {
		return this.address.getPin();
	}
	public void setPin(Integer pin) {
		this.address.setPin(pin);
	}
	public String getCity() {
		return this.address.getCity();
	}
	public void setCity(String city) {
		this.address.setCity(city);
	}
	
	public List<Long> getContact() {
		return contact;
	}
	public void setContact(List<Long> contact) {
		this.contact = contact;
	}

	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee employee) {
		this.manager = employee;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
