package com.rk.service;

import java.util.List;

import com.rk.model.Category;

public interface CategoryService {
	public void addCategory(Category category);
	public List<Category> listCategories();
	public Category getCategory(Integer hsn);
	public void deleteCategory(Category category);
}
