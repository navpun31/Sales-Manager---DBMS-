package com.rk.dao;

import java.util.List;

import com.rk.model.Category;

public interface CategoryDao {
	public void addCategory(Category category);
	public List<Category> listCategories();
	public Category getCategory(Integer hsn);
	public void deleteCategory(Category category);
}
