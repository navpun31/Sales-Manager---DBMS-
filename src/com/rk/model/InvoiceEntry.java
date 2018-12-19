package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.rk.bean.InvoiceEntryKey;

@Entity
@Table(name="InvoiceEntry")
@IdClass(InvoiceEntryKey.class)
public class InvoiceEntry {
	@Id	
	@Column(name = "invoiceID")
	private Integer invoiceID;
	
	@Id	
	@Column(name = "productID")
	private String productID;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private Integer price;
	
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

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
