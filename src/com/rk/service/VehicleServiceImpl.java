package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.VehicleDao;
import com.rk.model.Vehicle;

@Service("vehicleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleDao vehicleDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addVehicle(Vehicle vehicle) {
		vehicleDao.addVehicle(vehicle);
	}

	public List<Vehicle> listVehicles() {
		return vehicleDao.listVehicles();
	}

	public List<Vehicle> listAgentVehicles(Integer agentID) {
		return vehicleDao.listAgentVehicles(agentID);
	}
	
	public Vehicle getVehicle(String vehicleNo) {
		return vehicleDao.getVehicle(vehicleNo);
	}

	public void deleteVehicle(Vehicle vehicle) {
		vehicleDao.deleteVehicle(vehicle);
	}

	public void addContact(Vehicle vehicle, List<Long> contact) {
		vehicleDao.addContact(vehicle, contact);
	}

	public List<Long> getContact(Vehicle vehicle) {
		return vehicleDao.getContact(vehicle);
	}
}
