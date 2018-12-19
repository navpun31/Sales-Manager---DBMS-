package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.rk.bean.ReturnEntryKey;

@Entity
@Table(name="returnEntry")
@IdClass(ReturnEntryKey.class)
public class ReturnEntry {
	@Id	
	@Column(name = "creditNoteNo")
	private Integer creditNoteNo;
	
	@Id	
	@Column(name = "invoiceID")
	private Integer invoiceID;
	
	@Id	
	@Column(name = "productID")
	private String productID;

	@Column(name = "quantity")
	private Integer quantity;

	public Integer getCreditNoteNo() {
		return creditNoteNo;
	}
	public void setCreditNoteNo(Integer creditNoteNo) {
		this.creditNoteNo = creditNoteNo;
	}

	public Integer getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Integer invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
