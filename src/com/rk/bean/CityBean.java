package com.rk.bean;

import javax.validation.constraints.NotNull;

public class CityBean {
	@NotNull
	private String city;

	@NotNull
	private String state;

	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
