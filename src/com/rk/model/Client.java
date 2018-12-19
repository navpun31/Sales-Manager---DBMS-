package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Client")
public class Client {
	@Id	
	@NotNull
	@Column(name = "id")
	private Integer id = 0;

	@NotNull
	@Column(name = "gstin")
	private String gstin;

	@NotNull
	@Column(name = "firmName")
	private String firmName;

	@NotNull
	@Column(name = "addressLine1")
	private String addressLine1;
	
	@NotNull
	@Column(name = "addressLine2")
	private String addressLine2;
	
	@NotNull
	@Column(name = "pin")
	private Integer pin; 
	
	@NotNull
	@Column(name = "city")
	private String city;
	
	@NotNull
	@Column(name = "landline")
	private Long landline;

	@NotNull
	@Column(name = "mobile")
	private Long mobile;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "bankName")
	private String bankName;

	@NotNull
	@Column(name = "bankBranch")
	private String bankBranch;

	@NotNull
	@Column(name = "bankAccountNo")
	private String bankAccountNo;
	
	@NotNull
	@Column(name = "ifsc")
	private String ifsc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = 0;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return this.addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Integer getPin() {
		return this.pin;
	}
	
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public Long getLandline() {
		return landline;
	}

	public void setLandline(Long landline) {
		this.landline = landline;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

}
