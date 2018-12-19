package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="State")
public class State {
	@Id
	@Column(name = "state")
	private String state;
	
	@Column(name = "stateCode")
	private Integer stateCode;
	
	@Column(name = "country")
	private String country;

	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
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
