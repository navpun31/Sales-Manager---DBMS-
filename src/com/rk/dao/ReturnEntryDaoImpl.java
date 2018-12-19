package com.rk.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.bean.ReturnEntryKey;
import com.rk.model.ReturnEntry;

@Repository("returnEntryDao")
public class ReturnEntryDaoImpl implements ReturnEntryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addReturnEntry(ReturnEntry returnEntry) {
		sessionFactory.getCurrentSession().saveOrUpdate(returnEntry);
	}

	@SuppressWarnings("unchecked")
	public List<ReturnEntry> listReturnEntries() {
		return (List<ReturnEntry>) sessionFactory.getCurrentSession().createCriteria(ReturnEntry.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReturnEntry> listReturnEntries(Integer creditNoteNo) {
		return (List<ReturnEntry>) sessionFactory.getCurrentSession().createQuery("From ReturnEntry where creditNoteNo = " + creditNoteNo).list();
	}

	public ReturnEntry getReturnEntry(ReturnEntryKey returnEntryKey) {
		return (ReturnEntry) sessionFactory.getCurrentSession().get(ReturnEntry.class, (Serializable) returnEntryKey);
	}

	public void deleteReturnEntry(ReturnEntry returnEntry) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM returnEntry WHERE creditNoteNo = " + returnEntry.getCreditNoteNo() + " and invoiceID = " + returnEntry.getInvoiceID() + " and productID = '" + returnEntry.getProductID() + "'").executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public Integer getQuantity(ReturnEntryKey returnEntryKey) {
		List<BigDecimal> l = (List<BigDecimal>) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT SUM(quantity) FROM returnEntry "
				+ "WHERE invoiceID = " + returnEntryKey.getInvoiceID() + " "
						+ "and productID = '" + returnEntryKey.getProductID() + "' "
								+ "and creditNoteNo <> " + returnEntryKey.getCreditNoteNo()).list();
		if(l == null || l.isEmpty() || l.get(0) == null) {
			return 0;
		}
		return Integer.parseInt(l.get(0).toString());
	}

}
