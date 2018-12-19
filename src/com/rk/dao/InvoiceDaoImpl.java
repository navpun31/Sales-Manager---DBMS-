package com.rk.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Invoice;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements InvoiceDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addInvoice(Invoice invoice) {
		sessionFactory.getCurrentSession().saveOrUpdate(invoice);
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> listInvoices() {
		return (List<Invoice>) sessionFactory.getCurrentSession().createCriteria(Invoice.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Invoice> listInvoices(String partyID) {
		return (List<Invoice>) sessionFactory.getCurrentSession().createQuery("From Invoice where partyID = '" + partyID + "'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Invoice> listUnpaidInvoices(String partyID) {
		return (List<Invoice>) sessionFactory.getCurrentSession().createQuery("From Invoice where partyID = '" + partyID + "' and paymentID IS NULL").list();
	}

	public Invoice getInvoice(Integer invoiceID) {
		return (Invoice) sessionFactory.getCurrentSession().get(Invoice.class, invoiceID);
	}

	public void deleteInvoice(Invoice invoice) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Invoice WHERE invoiceID = " + invoice.getInvoiceID()).executeUpdate();
	}

	public void addTax(Invoice invoice, List<String> taxes) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM invoiceTax WHERE invoiceID = " + invoice.getInvoiceID()).executeUpdate();
		if(taxes.isEmpty())	return;
		for(String T : taxes) {
			if(T == null || T.isEmpty() || T.equals("") || T=="") continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO invoiceTax VALUES(" + invoice.getInvoiceID() + ", '" + T + "')").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTax(Invoice invoice) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT taxType FROM Tax WHERE taxType IN (SELECT taxType FROM invoiceTax WHERE invoiceID = " + invoice.getInvoiceID() + ")").list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer getSubtotal(Invoice invoice) {
		List<BigDecimal> l = (List<BigDecimal>) sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(price*quantity) FROM InvoiceEntry WHERE invoiceID = " + invoice.getInvoiceID()).list();
		if(l == null || l.isEmpty() || l.get(0) == null) {
			return 0;
		}
		return (int) Math.round(Double.parseDouble(l.get(0).toString()));
	}

	@SuppressWarnings("unchecked")
	public Integer getQuantity(Invoice invoice) {
		List<BigDecimal> l = (List<BigDecimal>) sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(quantity) FROM InvoiceEntry WHERE invoiceID = " + invoice.getInvoiceID()).list();
		if(l == null || l.isEmpty() || l.get(0) == null) {
			return 0;
		}
		return (int) Math.round(Double.parseDouble(l.get(0).toString()));
	}
	
	@SuppressWarnings("unchecked")
	public Double getTotalTax(Invoice invoice) {
		List<BigDecimal> l = (List<BigDecimal>) sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(percent) FROM invoiceTax as i, Tax as t WHERE i.taxType = t.taxType and i.invoiceID = " + invoice.getInvoiceID()).list();
		if(l == null || l.isEmpty() || l.get(0) == null) {
			return 0.0;
		}
		return (double) Double.parseDouble(l.get(0).toString());
	}
	
	@SuppressWarnings("unchecked")
	public Integer getInvoiceProfit(Integer invoiceID) {
		List<BigDecimal> l = (List<BigDecimal>) sessionFactory.getCurrentSession().createSQLQuery(""
				+ "SELECT (PROF.sell-PROF.cost) as profit FROM " + 
				"(SELECT SUM(price*quantity) as sell, " + 
				"( SELECT SUM(P.cost*IE.quantity) FROM Product as P, InvoiceEntry as IE " + 
				"	WHERE P.productID = IE.productID and invoiceID = " + invoiceID + " ) as cost " + 
				"FROM InvoiceEntry as I WHERE invoiceID = " + invoiceID + ") as PROF").list();
		if(l == null || l.isEmpty() || l.get(0) == null) {
			return 0;
		}
		return (int) Math.round(Double.parseDouble(l.get(0).toString()));
	}
}
