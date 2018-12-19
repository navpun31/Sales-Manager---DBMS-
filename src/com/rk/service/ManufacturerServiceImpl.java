package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.*;
import com.rk.model.*;

@Service("manufacturerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ManufacturerServiceImpl implements ManufacturerService {
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addManufacturer(Manufacturer manufacturer) {
		manufacturerDao.addManufacturer(manufacturer);
	}
	
	public List<Manufacturer> listManufacturers() {
		return manufacturerDao.listManufacturers();
	}
	
	public Manufacturer getManufacturer(Integer manuID) {
		return manufacturerDao.getManufacturer(manuID);
	}
	
	public void deleteManufacturer(Manufacturer manufacturer) {
		manufacturerDao.deleteManufacturer(manufacturer);
	}
	
	public void addContact(Manufacturer manufacturer, List<Long> contact) {
		manufacturerDao.addContact(manufacturer, contact);
	}
	
	public List<Long> getContact(Manufacturer manufacturer) {
		return manufacturerDao.getContact(manufacturer);
	}
}
