package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.CityDao;
import com.rk.model.City;

@Service("cityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCity(City city) {
		cityDao.addCity(city);
	}

	public List<City> listCities() {
		return cityDao.listCities();
	}

	public City getCity(String city) {
		return cityDao.getCity(city);
	}

	public void deleteCity(City city) {
		cityDao.deleteCity(city);
	}
}
