package com.rk.service;

import java.util.List;

import com.rk.model.City;

public interface CityService {
	public void addCity(City city);
	public List<City> listCities();
	public City getCity(String city);
	public void deleteCity(City city);
}
