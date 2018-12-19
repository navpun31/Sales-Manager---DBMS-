package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.*;

@Repository("warehouseDao")
public class WarehouseDaoImpl implements WarehouseDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addWarehouse(Warehouse warehouse) {
		sessionFactory.getCurrentSession().saveOrUpdate(warehouse);
	}

	@SuppressWarnings("unchecked")
	public List<Warehouse> listWarehouses() {
		return (List<Warehouse>) sessionFactory.getCurrentSession().createCriteria(Warehouse.class).list();
	}

	public Warehouse getWarehouse(Integer warehouseID) {
		return (Warehouse) sessionFactory.getCurrentSession().get(Warehouse.class, warehouseID);
	}

	public void deleteWarehouse(Warehouse warehouse) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Warehouse WHERE warehouseID = " + warehouse.getWarehouseID()).executeUpdate();
	}

	public void addContact(Warehouse warehouse, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM warehouseContact WHERE warehouseID = " + warehouse.getWarehouseID()).executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO warehouseContact VALUES(" + warehouse.getWarehouseID() + ", " + L + ")").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(Warehouse warehouse) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM warehouseContact WHERE warehouseID = " + warehouse.getWarehouseID()).list();
	}

	public void setManagerNull(Integer employeeID) {
		sessionFactory.getCurrentSession().createSQLQuery("UPDATE Warehouse SET managerID = NULL WHERE managerID = " + employeeID).executeUpdate();
	}
}
