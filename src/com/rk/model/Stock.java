package com.rk.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Stock")
public class Stock {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "stockID")
	private Integer stockID;

	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "dateOfManu")
	private Date dateOfManu;
	
	@Column(name = "manuID")
	private Integer manuID;

	@Column(name = "productID")
	private String productID;

	public Integer getStockID() {
		return stockID;
	}
	public void setStockID(Integer stockID) {
		this.stockID = stockID;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDateOfManu() {
		return dateOfManu;
	}
	public void setDateOfManu(Date dateOfManu) {
		this.dateOfManu = dateOfManu;
	}

	public Integer getManuID() {
		return manuID;
	}
	public void setManuID(Integer manuID) {
		this.manuID = manuID;
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
}
