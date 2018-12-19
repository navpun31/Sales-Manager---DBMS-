package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.rk.model.Category;

public class ProductBean {
	@NotNull
	private String productID;
	
	@NotNull
	private String name;

	private String description;
	
	@NotNull
	private Integer cost;
	
	@NotNull
	private Integer categoryHSN;
	private Category category;
	
	private List<String> size;
	private List<String> color;
	private List<ImageBean> image;
	
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
	
	public List<String> getSize() {
		return size;
	}
	public void setSize(List<String> size) {
		this.size = size;
	}
	
	public List<String> getColor() {
		return color;
	}
	public void setColor(List<String> color) {
		this.color = color;
	}
	public List<ImageBean> getImage() {
		return image;
	}
	public void setImage(List<ImageBean> image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
