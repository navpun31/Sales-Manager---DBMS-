package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Color;

@Repository("colorDao")
public class ColorDaoImpl implements ColorDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addColor(Color color) {
		sessionFactory.getCurrentSession().saveOrUpdate(color);
	}

	@SuppressWarnings("unchecked")
	public List<Color> listColors() {
		return (List<Color>) sessionFactory.getCurrentSession().createCriteria(Color.class).list();
	}

	public Color getColor(String colorCode) {
		return (Color) sessionFactory.getCurrentSession().get(Color.class, colorCode);
	}

	public void deleteColor(Color color) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Color WHERE colorCode = '" + color.getColorCode() + "'").executeUpdate();
	}

}
