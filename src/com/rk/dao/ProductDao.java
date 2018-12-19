package com.rk.dao;

import java.util.List;

import com.rk.model.Product;

public interface ProductDao {
	public void addProduct(Product product);
	public List<Product> listProducts();
	public List<Product> listCategoryProducts(Integer categoryHsn);
	public List<Object[]> listColorProducts(String colorCode);
	public Product getProduct(String productID);
	public void deleteProduct(Product product);
	public void addSize(Product product, List<String> size);
	public List<String> getSize(Product product);
	public void addColor(Product product, List<String> color);
	public List<String> getColor(Product product);
	public List<Object[]> listQuantitySold();
	public List<Object[]> listQuantityReturned();
}
