package com.rk.bean;

import javax.validation.constraints.NotNull;

public class StateBean {
	@NotNull
	private String state;

	@NotNull
	private Integer stateCode;

	@NotNull
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
