package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TransportAgent")
public class TransportAgent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "agentID")
	private Integer agentID;
	
	@Column(name = "companyName")
	private String companyName;

	@Column(name = "perDeliveryPay")
	private Integer perDeliveryPay;
	
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
}
