package com.rk.bean;

import javax.validation.constraints.NotNull;

public class CategoryBean {
	@NotNull
	private Integer hsn;
	
	@NotNull
	private String name;

	private String description;

	public Integer getHsn() {
		return hsn;
	}
	public void setHsn(Integer hsn) {
		this.hsn = hsn;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
