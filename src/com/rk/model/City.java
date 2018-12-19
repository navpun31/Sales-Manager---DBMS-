package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="City")
public class City {
	@Id
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
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
