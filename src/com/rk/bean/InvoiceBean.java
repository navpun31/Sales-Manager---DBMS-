package com.rk.bean;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.rk.model.Party;
import com.rk.model.Payment;
import com.rk.model.Vehicle;

public class InvoiceBean {
	private Integer invoiceID;

	@NotNull
	private Date dateOfIssue;
	
	private Double discount = 0.0;

	private Integer freight = 0;

	private String ewayNo;

	private String lorryReceiptNo;
	
	private Integer profit;
	
	@NotNull
	private String partyID;
	private Party party;
	
	@NotNull
	private String vehicleID;
	private Vehicle vehicle;
	
	private Integer paymentID;
	private Payment payment;
	
	@NotNull
	private List<String> tax;

	private Double totalTax;
	private Integer subtotal;
	private Integer quantity;
	
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
	
	public List<String> getTax() {
		return tax;
	}
	public void setTax(List<String> tax) {
		this.tax = tax;
	}
	
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	
	public Double getDiscountAmount() {
		return this.getDiscount()*(new Double(this.subtotal)/100.0);
	}
	public Double getTaxableValue() {
		return new Double(this.subtotal) 
				- this.getDiscountAmount()
				+ new Double(this.getFreight());
	}
	public Double getTotal() {
		return this.getTaxableValue() 
				+ this.getTaxableValue()*this.getTotalTax()/100.0;
	}
	public Integer getRoundedTotal() {
		return new Integer((int)Math.round(this.getTotal()));
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}
	
	public String getTotalWords() {
		return "";
	}
	public String getTaxWords() {
		return "";
	}
	
	public Boolean getClear() {
		return this.getPaymentID()!=null;
	}
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
}
