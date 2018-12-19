package com.rk.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.rk.model.TransportAgent;

public class VehicleBean {
	@NotNull
	private String vehicleNo;

	private String type;

	@NotNull
	private String driverName;
	
	@NotNull
	private Integer agentID;
	private TransportAgent transportAgent;
	
	private List<Long> contact;

	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getAgentID() {
		return agentID;
	}
	public void setAgentID(Integer agentID) {
		this.agentID = agentID;
	}
	
	public List<Long> getContact() {
		return contact;
	}
	public void setContact(List<Long> contact) {
		this.contact = contact;
	}
	public TransportAgent getTransportAgent() {
		return transportAgent;
	}
	public void setTransportAgent(TransportAgent transportAgent) {
		this.transportAgent = transportAgent;
	}
}
