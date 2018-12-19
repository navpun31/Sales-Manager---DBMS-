package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.bean.InvoiceEntryKey;
import com.rk.dao.InvoiceEntryDao;
import com.rk.model.InvoiceEntry;

@Service("invoiceEntryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InvoiceEntryServiceImpl implements InvoiceEntryService {
	@Autowired
	private InvoiceEntryDao invoiceEntryDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addInvoiceEntry(InvoiceEntry invoiceEntry) {
		invoiceEntryDao.addInvoiceEntry(invoiceEntry);
	}

	public List<InvoiceEntry> listInvoiceEntries() {
		return invoiceEntryDao.listInvoiceEntries();
	}

	public List<InvoiceEntry> listInvoiceEntries(Integer invoiceID) {
		return invoiceEntryDao.listInvoiceEntries(invoiceID);
	}

	public InvoiceEntry getInvoiceEntry(InvoiceEntryKey invoiceEntryKey) {
		return invoiceEntryDao.getInvoiceEntry(invoiceEntryKey);
	}

	public void deleteInvoiceEntry(InvoiceEntry invoiceEntry) {
		invoiceEntryDao.deleteInvoiceEntry(invoiceEntry);
	}
	
}
