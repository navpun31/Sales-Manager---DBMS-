package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.CategoryDao;
import com.rk.model.Category;

@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	public List<Category> listCategories() {
		return categoryDao.listCategories();
	}

	public Category getCategory(Integer hsn) {
		return categoryDao.getCategory(hsn);
	}

	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}
}
