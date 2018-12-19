package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.CreditNote;

@Repository("creditNoteDao")
public class CreditNoteDaoImpl implements CreditNoteDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addCreditNote(CreditNote creditNote) {
		sessionFactory.getCurrentSession().saveOrUpdate(creditNote);
	}

	@SuppressWarnings("unchecked")
	public List<CreditNote> listCreditNotes() {
		return (List<CreditNote>) sessionFactory.getCurrentSession().createCriteria(CreditNote.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<CreditNote> listCreditNotes(String partyID) {
		return (List<CreditNote>) sessionFactory.getCurrentSession().createQuery("From CreditNote where partyID = '" + partyID + "'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CreditNote> listUnpaidCreditNotes(String partyID) {
		return (List<CreditNote>) sessionFactory.getCurrentSession().createQuery("From CreditNote where partyID = '" + partyID + "' and paymentID IS NULL").list();
	}

	public CreditNote getCreditNote(Integer creditNoteNo) {
		return (CreditNote) sessionFactory.getCurrentSession().get(CreditNote.class, creditNoteNo);
	}

	public void deleteCreditNote(CreditNote creditNote) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM CreditNote WHERE creditNoteNo = " + creditNote.getCreditNoteNo()).executeUpdate();
	}

}
