package com.rk.bean;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ColorBean {
	@Id	
	@NotNull
	private String colorCode;

	@NotNull
	@Min(0) @Max(255)
	private Integer red;
	
	@NotNull
	@Min(0) @Max(255)
	private Integer green;
	
	@NotNull
	@Min(0) @Max(255)
	private Integer blue;

	@NotNull
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
