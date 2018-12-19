package com.rk.dao;

import java.util.List;
import com.rk.model.Manufacturer;

public interface ManufacturerDao {
	public void addManufacturer(Manufacturer manufacturer);
	public List<Manufacturer> listManufacturers();
	public Manufacturer getManufacturer(Integer manuID);
	public void deleteManufacturer(Manufacturer manufacturer);
	public void addContact(Manufacturer manufacturer, List<Long> contact);
	public List<Long> getContact(Manufacturer manufacturer);
}
