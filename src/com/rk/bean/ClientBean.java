package com.rk.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class ClientBean {
	private Integer id = 0;

	@NotNull
	private String gstin;

	@NotNull
	private String firmName;

	@NotNull
	private Address address;
	
	@NotNull
	private Long landline;

	@NotNull
	private Long mobile;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String bankName;

	@NotNull
	private String bankBranch;

	@NotNull
	private String bankAccountNo;
	
	@NotNull
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getAddressLine1() {
		return this.address.getAddressLine1();
	}
	public void setAddressLine1(String addressLine1) {
		this.address.setAddressLine1(addressLine1);
	}
	public String getAddressLine2() {
		return this.address.getAddressLine2();
	}
	public void setAddressLine2(String addressLine2) {
		this.address.setAddressLine2(addressLine2);
	}
	public Integer getPin() {
		return this.address.getPin();
	}
	public void setPin(Integer pin) {
		this.address.setPin(pin);
	}
	public String getCity() {
		return this.address.getCity();
	}
	public void setCity(String city) {
		this.address.setCity(city);
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
