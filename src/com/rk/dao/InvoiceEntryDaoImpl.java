package com.rk.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.bean.InvoiceEntryKey;
import com.rk.model.InvoiceEntry;

@Repository("invoiceEntryDao")
public class InvoiceEntryDaoImpl implements InvoiceEntryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addInvoiceEntry(InvoiceEntry invoiceEntry) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoiceEntry);
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceEntry> listInvoiceEntries() {
		return (List<InvoiceEntry>) sessionFactory.getCurrentSession().createCriteria(InvoiceEntry.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<InvoiceEntry> listInvoiceEntries(Integer invoiceID) {
		return (List<InvoiceEntry>) sessionFactory.getCurrentSession().createQuery("From InvoiceEntry where invoiceID = " + invoiceID).list();
	}

	public InvoiceEntry getInvoiceEntry(InvoiceEntryKey invoiceEntryKey) {
		return (InvoiceEntry) sessionFactory.getCurrentSession().get(InvoiceEntry.class, (Serializable) invoiceEntryKey);
	}

	public void deleteInvoiceEntry(InvoiceEntry invoiceEntry) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM InvoiceEntry WHERE invoiceID = " + invoiceEntry.getInvoiceID() + " and productID = '" + invoiceEntry.getProductID() + "'").executeUpdate();
		
	}
}
