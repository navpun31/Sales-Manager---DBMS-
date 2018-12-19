package com.rk.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Invoice")
public class Invoice {
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "invoiceID")
	private Integer invoiceID;

	@Column(name = "dateOfIssue")
	private Date dateOfIssue;
	
	@Column(name = "discount")
	private Double discount;

	@Column(name = "freight")
	private Integer freight;

	@Column(name = "ewayNo")
	private String ewayNo;

	@Column(name = "lorryReceiptNo")
	private String lorryReceiptNo;
	
	@Column(name = "partyID")
	private String partyID;
	
	@Column(name = "vehicleID")
	private String vehicleID;
	
	@Column(name = "paymentID")
	private Integer paymentID;

	public Integer getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Integer invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getEwayNo() {
		return ewayNo;
	}
	public void setEwayNo(String ewayNo) {
		this.ewayNo = ewayNo;
	}

	public String getLorryReceiptNo() {
		return lorryReceiptNo;
	}
	public void setLorryReceiptNo(String lorryReceiptNo) {
		this.lorryReceiptNo = lorryReceiptNo;
	}

	public String getPartyID() {
		return partyID;
	}
	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}

	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public Integer getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}
	
}
