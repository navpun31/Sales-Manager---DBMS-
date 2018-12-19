package com.rk.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Tax;

@Repository("taxDao")
public class TaxDaoImpl implements TaxDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addTax(Tax tax) {
		sessionFactory.getCurrentSession().saveOrUpdate(tax);
	}

	@SuppressWarnings("unchecked")
	public List<Tax> listTaxes() {
		return (List<Tax>) sessionFactory.getCurrentSession().createCriteria(Tax.class).list();
	}
	
	public List<Tax> listTaxes(List<String> taxes){
		if(taxes == null)	return null;
		List<Tax> taxList = new ArrayList<Tax>();
		for(String tax : taxes) {
			taxList.add(getTax(tax));
		}
		return taxList;
	}
	
	public Tax getTax(String taxType) {
		return (Tax) sessionFactory.getCurrentSession().get(Tax.class, taxType);
	}

	public void deleteTax(Tax tax) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Tax WHERE taxType = '" + tax.getTaxType() + "'").executeUpdate();
	}

}
