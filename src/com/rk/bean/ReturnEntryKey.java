package com.rk.bean;

import java.io.Serializable;
import javax.persistence.Column;

public class ReturnEntryKey implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;
	@Column(name = "creditNoteNo")
	private Integer creditNoteNo;
	@Column(name="invoiceID")
	private Integer invoiceID;
	@Column(name="productID")
	private String productID;

	public ReturnEntryKey() {
	}
	public ReturnEntryKey(Integer creditNoteNo, Integer invoiceID, String productID) {
		this.setCreditNoteNo(creditNoteNo);
		this.setInvoiceID(invoiceID);
		this.setProductID(productID);
	}

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
	
}
