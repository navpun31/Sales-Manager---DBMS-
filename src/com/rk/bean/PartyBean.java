package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.rk.model.PartyAgent;

public class PartyBean {
	@NotNull
	private String gstin;
	
	@NotNull
	private String firmName;
	
	@NotNull
	private Address address;
	private Integer agentID;
	private PartyAgent partyAgent;
	
	private List<Long> contact;
	private List<String> email;
	
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
	
	public Address getAddress() {
		return this.address;
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
	
	public Integer getAgentID() {
		return this.agentID;
	}
	public void setAgentID(Integer id) {
		this.agentID = id;
	}

	public PartyAgent getPartyAgent() {
		return partyAgent;
	}
	public void setPartyAgent(PartyAgent partyAgent) {
		this.partyAgent = partyAgent;
	}
	
	public List<Long> getContact() {
		return this.contact;
	}
	public void setContact(List<Long> contact) {
		this.contact = contact;
	}
	
	public List<String> getEmail() {
		return this.email;
	}
	public void setEmail(List<String> email) {
		this.email = email;
	}
}


