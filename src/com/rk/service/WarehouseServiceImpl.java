package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.*;
import com.rk.model.*;

@Service("warehouseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WarehouseServiceImpl implements WarehouseService {
	@Autowired
	private WarehouseDao warehouseDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addWarehouse(Warehouse warehouse) {
		warehouseDao.addWarehouse(warehouse);
	}
	
	public List<Warehouse> listWarehouses() {
		return warehouseDao.listWarehouses();
	}
	
	public Warehouse getWarehouse(Integer warehouseID) {
		return warehouseDao.getWarehouse(warehouseID);
	}
	
	public void deleteWarehouse(Warehouse warehouse) {
		warehouseDao.deleteWarehouse(warehouse);
	}
	
	public void addContact(Warehouse warehouse, List<Long> contact) {
		warehouseDao.addContact(warehouse, contact);
	}
	
	public List<Long> getContact(Warehouse warehouse) {
		return warehouseDao.getContact(warehouse);
	}

	public void setManagerNull(Integer employeeID) {
		warehouseDao.setManagerNull(employeeID);
	}
}
