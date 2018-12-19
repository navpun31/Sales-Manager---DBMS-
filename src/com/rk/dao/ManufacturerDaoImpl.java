package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.*;

@Repository("manufacturerDao")
public class ManufacturerDaoImpl implements ManufacturerDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addManufacturer(Manufacturer manufacturer) {
		sessionFactory.getCurrentSession().saveOrUpdate(manufacturer);
	}

	@SuppressWarnings("unchecked")
	public List<Manufacturer> listManufacturers() {
		return (List<Manufacturer>) sessionFactory.getCurrentSession().createCriteria(Manufacturer.class).list();
	}

	public Manufacturer getManufacturer(Integer manuID) {
		return (Manufacturer) sessionFactory.getCurrentSession().get(Manufacturer.class, manuID);
	}

	public void deleteManufacturer(Manufacturer manufacturer) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Manufacturer WHERE manuID = " + manufacturer.getManuID()).executeUpdate();
	}

	public void addContact(Manufacturer manufacturer, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM manuContact WHERE manuID = " + manufacturer.getManuID()).executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO manuContact VALUES(" + manufacturer.getManuID() + ", " + L + ")").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(Manufacturer manufacturer) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM manuContact WHERE manuID = " + manufacturer.getManuID()).list();
	}
}
