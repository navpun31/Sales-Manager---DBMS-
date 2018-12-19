package com.rk.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Image;

@Repository("imageDao")
public class ImageDaoImpl implements ImageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Image image) {
		Session session = sessionFactory.getCurrentSession();
		session.save(image);
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> list() {
		return (List<Image>) sessionFactory.getCurrentSession().createCriteria(Image.class).list();
	}
	
	public Image get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Image)session.get(Image.class, id);
	}

	public void remove(Integer id) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Image WHERE imageID = " + id).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Image> list(String productID) {
		return (List<Image>)sessionFactory.getCurrentSession().createQuery("from Image where productID = '" + productID + "'").list();
	}
}
