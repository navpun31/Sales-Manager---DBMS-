package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Party")
public class Party {
	@Id
	@Column(name = "gstin")
	private String gstin;
	
	@Column(name = "firmName")
	private String firmName;
	
	@Column(name = "addressLine1")
	private String addressLine1;
	
	@Column(name = "addressLine2")
	private String addressLine2;
	
	@Column(name = "pin")
	private Integer pin; 
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "agentID")
	private Integer agentID;
	
	public String getGstin() {
		return this.gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	
	public String getFirmName() {
		return this.firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
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
	
	public Integer getAgentID() {
		return this.agentID;
	}
	public void setAgentID(Integer id) {
		this.agentID = id;
	}
}
