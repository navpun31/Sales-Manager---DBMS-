package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

public class TransportAgentBean {
	private Integer agentID;
	
	@NotNull
	private String companyName;

	private Integer perDeliveryPay;
	
	private List<Long> contact;
	private List<String> email;
	
	public Integer getAgentID() {
		return this.agentID;
	}
	public void setAgentID(Integer id) {
		this.agentID = id;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Integer getPerDeliveryPay() {
		return perDeliveryPay;
	}
	public void setPerDeliveryPay(Integer perDeliveryPay) {
		this.perDeliveryPay = perDeliveryPay;
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
