package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product {
	@Id	
	@Column(name = "productID")
	private String productID;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "cost")
	private Integer cost;
	
	@Column(name = "categoryHSN")
	private Integer categoryHSN;

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getCategoryHSN() {
		return categoryHSN;
	}
	public void setCategoryHSN(Integer categoryHSN) {
		this.categoryHSN = categoryHSN;
	}

}
