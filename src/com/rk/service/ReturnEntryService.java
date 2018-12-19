package com.rk.service;

import java.util.List;

import com.rk.bean.ReturnEntryKey;
import com.rk.model.ReturnEntry;

public interface ReturnEntryService {
	public void addReturnEntry(ReturnEntry returnEntry);
	public List<ReturnEntry> listReturnEntries();
	public List<ReturnEntry> listReturnEntries(Integer creditNoteNo);
	public ReturnEntry getReturnEntry(ReturnEntryKey returnEntryKey);
	public void deleteReturnEntry(ReturnEntry returnEntry);
	public Integer getQuantity(ReturnEntryKey returnEntryKey);
}
