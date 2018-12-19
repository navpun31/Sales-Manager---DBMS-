package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.bean.ReturnEntryKey;
import com.rk.dao.ReturnEntryDao;
import com.rk.model.ReturnEntry;

@Service("returnEntryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReturnEntryServiceImpl implements ReturnEntryService {
	@Autowired
	private ReturnEntryDao returnEntryDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addReturnEntry(ReturnEntry returnEntry) {
		returnEntryDao.addReturnEntry(returnEntry);
	}

	public List<ReturnEntry> listReturnEntries() {
		return returnEntryDao.listReturnEntries();
	}

	public List<ReturnEntry> listReturnEntries(Integer creditNoteNo) {
		return returnEntryDao.listReturnEntries(creditNoteNo);
	}

	public ReturnEntry getReturnEntry(ReturnEntryKey returnEntryKey) {
		return returnEntryDao.getReturnEntry(returnEntryKey);
	}

	public void deleteReturnEntry(ReturnEntry returnEntry) {
		returnEntryDao.deleteReturnEntry(returnEntry);
	}
	
	public Integer getQuantity(ReturnEntryKey returnEntryKey) {
		return returnEntryDao.getQuantity(returnEntryKey);
	}
}
