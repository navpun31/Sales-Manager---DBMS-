package com.rk.bean;

import java.io.Serializable;
import javax.persistence.Column;

public class InvoiceEntryKey implements Serializable {
	private static final long serialVersionUID = -723583058586873479L;
	@Column(name="invoiceID")
	private Integer invoiceID;
	@Column(name="productID")
	private String productID;

	public InvoiceEntryKey() {
	}
	public InvoiceEntryKey(Integer invoiceID, String productID) {
		this.setInvoiceID(invoiceID);
		this.setProductID(productID);
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
