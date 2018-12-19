package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addCategory(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
	}

	@SuppressWarnings("unchecked")
	public List<Category> listCategories() {
		return (List<Category>) sessionFactory.getCurrentSession().createCriteria(Category.class).list();
	}

	public Category getCategory(Integer hsn) {
		return (Category) sessionFactory.getCurrentSession().get(Category.class, hsn);
	}

	public void deleteCategory(Category category) {
		 sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Category WHERE hsn = " + category.getHsn()).executeUpdate();
	}

}
