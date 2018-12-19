package com.rk.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addProduct(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> listProducts() {
		return (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Product> listCategoryProducts(Integer categoryHsn) {
		return (List<Product>) sessionFactory.getCurrentSession().createQuery("From Product where categoryHsn = " + categoryHsn).list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> listColorProducts(String colorCode) {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT P.productID, P.name as pname, P.description, P.cost, C.name as cname, C.hsn "
				+ "FROM Product as P, Category as C, productColor as CLR "
				+ "WHERE P.categoryHsn = C.hsn and P.productID = CLR.productID and CLR.colorCode = '" + colorCode + "'"
					+ "").list();
	}

	public Product getProduct(String productID) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, productID);
	}

	public void deleteProduct(Product product) {
		 sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Product WHERE productID = '" + product.getProductID() + "'").executeUpdate();
	}

	public void addSize(Product product, List<String> size) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM productSize WHERE productID = '" + product.getProductID() + "'").executeUpdate();
		if(size==null || size.isEmpty())	return;
		for(String S : size) {
			if(S == null || S.isEmpty() || S.equals("") || S=="") continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO productSize VALUES('" + product.getProductID() + "', '" + S + "')").executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getSize(Product product) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT size FROM productSize WHERE productID = '" + product.getProductID() + "'").addScalar("size", Hibernate.STRING).list();
	}

	public void addColor(Product product, List<String> color) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM productColor WHERE productID = '" + product.getProductID() + "'").executeUpdate();
		if(color==null || color.isEmpty())	return;
		for(String S : color) {
			if(S == null || S.isEmpty() || S.equals("") || S=="") continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO productColor VALUES('" + product.getProductID() + "', '" + S + "')").executeUpdate();
		}		
	}

	@SuppressWarnings("unchecked")
	public List<String> getColor(Product product) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT colorCode FROM productColor WHERE productID = '" + product.getProductID() + "'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listQuantitySold() {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT PR.productID, S.sold "
				+ "FROM (Product as PR LEFT OUTER JOIN "
					+ "( SELECT P.productID as productID, SUM(I.quantity) as sold "
					+ "FROM InvoiceEntry as I, Product as P "
					+ "WHERE I.productID = P.productID GROUP BY P.productID ) as S "
				+ "ON PR.productID = S.productID)"
				+ "").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listQuantityReturned() {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT PR.productID, S.ret "
				+ "FROM (Product as PR LEFT OUTER JOIN "
					+ "( SELECT P.productID as productID, SUM(R.quantity) as ret "
					+ "FROM returnEntry as R, Product as P "
					+ "WHERE R.productID = P.productID GROUP BY P.productID ) as S "
				+ "ON PR.productID = S.productID)"
				+ "").list();
	}
	
}
