package com.rk.bean;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.rk.model.Party;
import com.rk.model.Payment;

public class CreditNoteBean {
	private Integer creditNoteNo;

	@NotNull
	private Date dateOfReturn;

	@NotNull
	private String partyID;
	private Party party;

	private Integer paymentID;
	private Payment payment;
	
	private Integer quantity;
	private Double total;

	public Integer getCreditNoteNo() {
		return creditNoteNo;
	}

	public void setCreditNoteNo(Integer creditNoteNo) {
		this.creditNoteNo = creditNoteNo;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public String getPartyID() {
		return partyID;
	}

	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getRoundedTotal() {
		return new Integer((int)Math.round(this.getTotal()));
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
}
