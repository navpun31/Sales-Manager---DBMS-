package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PartyAgent")
public class PartyAgent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "agentID")
	private Integer agentID;
	
	@Column(name = "name")
	private String name;

	@Column(name = "perPartyPay")
	private Integer perPartyPay;
	
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
}
