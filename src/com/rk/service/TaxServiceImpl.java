package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.TaxDao;
import com.rk.model.Tax;

@Service("taxService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaxServiceImpl implements TaxService {
	@Autowired
	private TaxDao taxDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTax(Tax tax) {
		taxDao.addTax(tax);
	}

	public List<Tax> listTaxes() {
		return taxDao.listTaxes();
	}
	
	public List<Tax> listTaxes(List<String> taxes) {
		return taxDao.listTaxes(taxes);
	}
	
	public Tax getTax(String taxType) {
		return taxDao.getTax(taxType);
	}

	public void deleteTax(Tax tax) {
		taxDao.deleteTax(tax);
	}
}
