package com.rk.service;

import java.util.List;
import com.rk.model.*;

public interface WarehouseService {
	public void addWarehouse(Warehouse warehouse);
	public List<Warehouse> listWarehouses();
	public Warehouse getWarehouse(Integer warehouseID);
	public void deleteWarehouse(Warehouse warehouse);
	public void addContact(Warehouse warehouse, List<Long> contact);
	public List<Long> getContact(Warehouse warehouse);
	public void setManagerNull(Integer employeeID);
}
