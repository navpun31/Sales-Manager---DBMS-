package com.rk.bean;

import javax.validation.constraints.NotNull;

import com.rk.model.Product;

public class ReturnEntryBean {
	private ReturnEntryKey key;
	
	@NotNull
	private Integer quantity;
	
	private Integer price;
	private Double tax;
	private Product product;

	public ReturnEntryKey getKey() {
		return key;
	}
	public void setKey(ReturnEntryKey key) {
		this.key = key;
	}

	public Integer getCreditNoteNo() {
		return this.key.getCreditNoteNo();
	}
	public void setCreditNoteNo(Integer creditNoteNo) {
		this.key.setCreditNoteNo(creditNoteNo);
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
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	public Integer getAmount() {
		return this.price*this.quantity;
	}
	public Double getTaxAmount() {
		return this.getTax()*(new Double(this.getAmount()));
	}
	public Double getTotal() {
		return this.getTaxAmount() + (new Double(this.getAmount()));
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
