package com.rk.bean;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.rk.model.Manufacturer;
import com.rk.model.Product;

public class StockBean {
	private Integer stockID;

	@NotNull
	private Integer quantity; 	
	
	@NotNull
	private Date dateOfManu;
	
	@NotNull
	private Integer manuID;
	private Manufacturer manufacturer;

	private String productID;
	private Product product;
	
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
//	private String dateOfManuParsed;
//	public String getDateOfManuParsed() {
//        if(this.dateOfManu == null)    return null;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        setDateOfManuParsed(df.format(this.dateOfManu));
//        return dateOfManuParsed;
//    }
//    public void setDateOfManuParsed(String dateOfManuParsed) {
//        this.dateOfManuParsed = dateOfManuParsed;
//    }
}
