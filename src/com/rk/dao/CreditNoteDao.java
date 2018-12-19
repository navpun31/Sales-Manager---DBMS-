package com.rk.dao;

import java.util.List;

import com.rk.model.CreditNote;

public interface CreditNoteDao {
	public void addCreditNote(CreditNote creditNote);
	public List<CreditNote> listCreditNotes();
	public List<CreditNote> listCreditNotes(String partyID);
	public List<CreditNote> listUnpaidCreditNotes(String partyID);
	public CreditNote getCreditNote(Integer creditNoteNo);
	public void deleteCreditNote(CreditNote creditNote);
}
