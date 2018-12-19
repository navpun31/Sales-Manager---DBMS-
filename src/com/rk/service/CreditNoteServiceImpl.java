package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.CreditNoteDao;
import com.rk.model.CreditNote;

@Service("creditNoteService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CreditNoteServiceImpl implements CreditNoteService {
	@Autowired
	private CreditNoteDao creditNoteDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCreditNote(CreditNote creditNote) {
		creditNoteDao.addCreditNote(creditNote);
	}

	public List<CreditNote> listCreditNotes() {
		return creditNoteDao.listCreditNotes();
	}
	
	public List<CreditNote> listCreditNotes(String partyID) {
		return creditNoteDao.listCreditNotes(partyID);
	}
	
	public List<CreditNote> listUnpaidCreditNotes(String partyID) {
		return creditNoteDao.listUnpaidCreditNotes(partyID);
	}

	public CreditNote getCreditNote(Integer creditNoteNo) {
		return creditNoteDao.getCreditNote(creditNoteNo);
	}

	public void deleteCreditNote(CreditNote creditNote) {
		creditNoteDao.deleteCreditNote(creditNote);
	}
}
