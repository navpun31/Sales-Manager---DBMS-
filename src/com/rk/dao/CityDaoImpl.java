package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.City;

@Repository("cityDao")
public class CityDaoImpl implements CityDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addCity(City city) {
		sessionFactory.getCurrentSession().saveOrUpdate(city);
	}

	@SuppressWarnings("unchecked")
	public List<City> listCities() {
		return (List<City>) sessionFactory.getCurrentSession().createCriteria(City.class).list();
	}

	public City getCity(String city) {
		return (City) sessionFactory.getCurrentSession().get(City.class, city);
	}

	public void deleteCity(City city) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM City WHERE city = '" + city.getCity() + "'").executeUpdate();
	}
}
