package com.rk.bean;

import java.sql.Blob;

import javax.persistence.Lob;

public class ImageBean {
	private Integer imageID;
	
	@Lob
	private Blob content;
	
	private String filename;

	private String contentType;
	
	private String productID;

	public Integer getImageID() {
		return imageID;
	}
	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
}
