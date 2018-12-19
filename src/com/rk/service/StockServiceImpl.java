package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.StockDao;
import com.rk.model.Stock;

@Service("stockService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockServiceImpl implements StockService {
	@Autowired
	private StockDao stockDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addStock(Stock stock) {
		stockDao.addStock(stock);
	}

	public List<Stock> listStocks() {
		return stockDao.listStocks();
	}

	public List<Stock> getProductStock(String productID) {
		return stockDao.getProductStock(productID);
	}
	
	public Stock getStock(Integer stockID) {
		return stockDao.getStock(stockID);
	}

	public void deleteStock(Stock stock) {
		stockDao.deleteStock(stock);
	}

	public List<Object[]> listStock() {
		return stockDao.listStock();
	}

}
