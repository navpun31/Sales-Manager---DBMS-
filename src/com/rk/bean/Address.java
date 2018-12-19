package com.rk.bean;

import javax.validation.constraints.NotNull;

public class Address {
	private String addressLine1;
	private String addressLine2;
	private Integer pin;
	@NotNull
	private String city;
	private String stateName;
	private Integer stateCode;
	private String country; 
	
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
//		TODO Set stateName/stateCode/country (from database)
		this.setStateName("Rajasthan");
		this.setStateCode(8);
		this.setCountry("India");
	}
	
	public String getStateName() {
		return this.stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getStateCode() {
		return this.stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
