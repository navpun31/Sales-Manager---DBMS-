package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Warehouse")
public class Warehouse {
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "warehouseID")
	private Integer warehouseID;

	@Column(name = "categoryHSN")
	private Integer categoryHSN;

	@Column(name = "managerID")
	private Integer managerID;
	
	@Column(name = "addressLine1")
	private String addressLine1;
	
	@Column(name = "addressLine2")
	private String addressLine2;
	
	@Column(name = "pin")
	private Integer pin; 
	
	@Column(name = "city")
	private String city;
	
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
	
	public String getAddressLine1() {
		return this.addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return this.addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Integer getPin() {
		return this.pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}