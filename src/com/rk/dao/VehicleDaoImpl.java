package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Vehicle;

@Repository("vehicleDao")
public class VehicleDaoImpl implements VehicleDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addVehicle(Vehicle vehicle) {
		sessionFactory.getCurrentSession().saveOrUpdate(vehicle);
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> listVehicles() {
		return (List<Vehicle>) sessionFactory.getCurrentSession().createCriteria(Vehicle.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> listAgentVehicles(Integer agentID) {
		return (List<Vehicle>) sessionFactory.getCurrentSession().createQuery("From Vehicle where agentID = " + agentID).list();
	}

	public Vehicle getVehicle(String vehicleNo) {
		return (Vehicle) sessionFactory.getCurrentSession().get(Vehicle.class, vehicleNo);
	}

	public void deleteVehicle(Vehicle vehicle) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Vehicle WHERE vehicleNo = '" + vehicle.getVehicleNo() + "'").executeUpdate();
	}

	public void addContact(Vehicle vehicle, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM driverContact WHERE vehicleNo = '" + vehicle.getVehicleNo() + "'").executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO driverContact VALUES('" + vehicle.getVehicleNo() + "', " + L + ")").executeUpdate();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(Vehicle vehicle) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM driverContact WHERE vehicleNo = '" + vehicle.getVehicleNo() + "'").list();
	}

}
