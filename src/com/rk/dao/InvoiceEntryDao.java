package com.rk.dao;

import java.util.List;

import com.rk.bean.InvoiceEntryKey;
import com.rk.model.InvoiceEntry;

public interface InvoiceEntryDao {
	public void addInvoiceEntry(InvoiceEntry invoiceEntry);
	public List<InvoiceEntry> listInvoiceEntries();
	public List<InvoiceEntry> listInvoiceEntries(Integer invoiceID);
	public InvoiceEntry getInvoiceEntry(InvoiceEntryKey invoiceEntryKey);
	public void deleteInvoiceEntry(InvoiceEntry invoiceEntry);
}
