package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Manufacturer")
public class Manufacturer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "manuID")
	private Integer manuID;

	@Column(name = "name")
	private String name;
	
	@Column(name = "addressLine1")
	private String addressLine1;
	
	@Column(name = "addressLine2")
	private String addressLine2;
	
	@Column(name = "pin")
	private Integer pin; 
	
	@Column(name = "city")
	private String city;
	
	public Integer getManuID() {
		return manuID;
	}
	public void setManuID(Integer manuID) {
		this.manuID = manuID;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
