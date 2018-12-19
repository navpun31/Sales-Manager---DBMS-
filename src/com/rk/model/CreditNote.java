package com.rk.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CreditNote")
public class CreditNote {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "creditNoteNo")
	private Integer creditNoteNo;

	@Column(name = "dateOfReturn")
	private Date dateOfReturn;

	@Column(name = "partyID")
	private String partyID;

	@Column(name = "paymentID")
	private Integer paymentID;

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
}
