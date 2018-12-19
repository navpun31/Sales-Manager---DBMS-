package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Color")
public class Color {
	@Id	
	@Column(name = "colorCode")
	private String colorCode;

	@Column(name = "R")
	private Integer red;
	
	@Column(name = "G")
	private Integer green;
	
	@Column(name = "B")
	private Integer blue;

	@Column(name = "colorName")
	private String colorName;

	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
	public Integer getRed() {
		return red;
	}
	public void setRed(Integer r) {
		this.red = r;
	}
	
	public Integer getGreen() {
		return green;
	}
	public void setGreen(Integer g) {
		this.green = g;
	}
	
	public Integer getBlue() {
		return blue;
	}
	public void setBlue(Integer b) {
		this.blue = b;
	}
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
}
