package com.rk.bean;

import javax.validation.constraints.NotNull;

public class TaxBean {
	@NotNull
	private String taxType;

	@NotNull
	private Double percent;

	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
}
