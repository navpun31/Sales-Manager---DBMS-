package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.ProductDao;
import com.rk.model.Product;

@Service("productService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	public List<Product> listProducts() {
		return productDao.listProducts();
	}

	public List<Product> listCategoryProducts(Integer categoryHsn) {
		return productDao.listCategoryProducts(categoryHsn);
	}
	
	public List<Object[]> listColorProducts(String colorCode) {
		return productDao.listColorProducts(colorCode);
	}

	public Product getProduct(String productID) {
		return productDao.getProduct(productID);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public void addSize(Product product, List<String> size) {
		productDao.addSize(product, size);
	}

	public List<String> getSize(Product product) {
		return productDao.getSize(product);
	}

	public void addColor(Product product, List<String> color) {
		productDao.addColor(product, color);
	}

	public List<String> getColor(Product product) {
		return productDao.getColor(product);
	}

	public List<Object[]> listQuantitySold() {
		return productDao.listQuantitySold();
	}

	public List<Object[]> listQuantityReturned() {
		return productDao.listQuantityReturned();
	}
}
