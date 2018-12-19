package com.rk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle")
public class Vehicle {
	@Id	
	@Column(name = "vehicleNo")
	private String vehicleNo;

	@Column(name = "type")
	private String type;

	@Column(name = "driverName")
	private String driverName;
	
	@Column(name = "agentID")
	private Integer agentID;

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
}
