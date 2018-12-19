package com.rk.dao;

import java.util.List;

import com.rk.model.Stock;

public interface StockDao {
	public void addStock(Stock stock);
	public List<Stock> listStocks();
	public List<Stock> getProductStock(String productID);
	public Stock getStock(Integer stockID);
	public void deleteStock(Stock stock);
	public List<Object[]> listStock();
}
