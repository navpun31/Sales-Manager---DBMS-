package com.rk.bean;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class PaymentBean {
	private Integer paymentID;

	@NotNull
	private Date dateOfPay;

	@NotNull
	private Integer amount;

	@NotNull
	private String modeOfPay;
	
	@NotNull
	private String type;

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getModeOfPay() {
		return modeOfPay;
	}

	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
