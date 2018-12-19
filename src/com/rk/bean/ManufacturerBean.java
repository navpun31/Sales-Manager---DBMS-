package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ManufacturerBean {
	private Integer manuID;

	@NotNull
	private String name;
	
	@NotNull
	private Address address;
	private List<Long> contact;
	
	public Integer getManuID() {
		return manuID;
	}
	public void setManuID(Integer manuID) {
		this.manuID = manuID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
