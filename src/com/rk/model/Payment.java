package com.rk.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Payment {
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "paymentID")
	private Integer paymentID;

	@Column(name = "dateOfPay")
	private Date dateOfPay;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "modeOfPay")
	private String modeOfPay;
	
	@Column(name = "type")
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
