package com.rk.bean;

import javax.validation.constraints.NotNull;

import com.rk.model.Product;

public class InvoiceEntryBean {
	private InvoiceEntryKey key;
	
	@NotNull
	private Integer quantity;

	@NotNull
	private Integer price;
	
	private Product product;
	
	public InvoiceEntryKey getKey() {
		return key;
	}
	public void setKey(InvoiceEntryKey key) {
		this.key = key;
	}
	
	public Integer getInvoiceID() {
		return this.key.getInvoiceID();
	}
	public void setInvoiceID(Integer invoiceID) {
		this.key.setInvoiceID(invoiceID);
	}
	public String getProductID() {
		return this.key.getProductID();
	}
	public void setProductID(String productID) {
		this.key.setProductID(productID);
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
	
	public Integer getAmount() {
		return this.price*this.quantity;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
