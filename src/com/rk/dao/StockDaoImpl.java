package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Stock;

@Repository("stockDao")
public class StockDaoImpl implements StockDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addStock(Stock stock) {
		sessionFactory.getCurrentSession().saveOrUpdate(stock);
	}

	@SuppressWarnings("unchecked")
	public List<Stock> listStocks() {
		return (List<Stock>) sessionFactory.getCurrentSession().createCriteria(Stock.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Stock> getProductStock(String productID) {
		return (List<Stock>) sessionFactory.getCurrentSession().createQuery("From Stock where productID = '" + productID + "'").list();
	}

	public Stock getStock(Integer stockID) {
		return (Stock) sessionFactory.getCurrentSession().get(Stock.class, stockID);
	}

	public void deleteStock(Stock stock) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Stock WHERE stockID = " + stock.getStockID()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> listStock() {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT P.productID, S.quantity "
				+ "FROM (Product as P LEFT OUTER JOIN "
					+ "( SELECT productID, SUM(quantity) as quantity FROM Stock GROUP BY productID ) as S "
					+ "ON P.productID = S.productID)"
				+ "").list();
	}
	
}
