package com.rk.service;

import java.util.List;

import com.rk.model.Tax;

public interface TaxService {
	public void addTax(Tax tax);
	public List<Tax> listTaxes();
	public List<Tax> listTaxes(List<String> taxes);
	public Tax getTax(String taxType);
	public void deleteTax(Tax tax);
}
