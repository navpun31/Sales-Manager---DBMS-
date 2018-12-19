package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

public class PartyAgentBean {
	private Integer agentID;
	
	@NotNull
	private String name;

	private Integer perPartyPay;
	
	private List<Long> contact;
	private List<String> email;
	
	public Integer getAgentID() {
		return this.agentID;
	}
	public void setAgentID(Integer id) {
		this.agentID = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPerPartyPay() {
		return this.perPartyPay;
	}
	public void setPerPartyPay(Integer perPartyPay) {
		this.perPartyPay = perPartyPay;
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
