package com.rk.dao;

import java.util.List;

import com.rk.model.City;

public interface CityDao {
	public void addCity(City city);
	public List<City> listCities();
	public City getCity(String city);
	public void deleteCity(City city);
}
