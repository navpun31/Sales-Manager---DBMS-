package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.InvoiceDao;
import com.rk.model.Invoice;

@Service("invoiceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addInvoice(Invoice invoice) {
		invoiceDao.addInvoice(invoice);
	}

	public List<Invoice> listInvoices() {
		return invoiceDao.listInvoices();
	}

	public List<Invoice> listInvoices(String partyID) {
		return invoiceDao.listInvoices(partyID);
	}
	
	public List<Invoice> listUnpaidInvoices(String partyID) {
		return invoiceDao.listUnpaidInvoices(partyID);
	}

	public Invoice getInvoice(Integer invoiceID) {
		return invoiceDao.getInvoice(invoiceID);
	}

	public void deleteInvoice(Invoice invoice) {
		invoiceDao.deleteInvoice(invoice);
	}

	public void addTax(Invoice invoice, List<String> taxes) {
		invoiceDao.addTax(invoice, taxes);
	}

	public List<String> getTax(Invoice invoice) {
		return invoiceDao.getTax(invoice);
	}

	public Integer getSubtotal(Invoice invoice) {
		return invoiceDao.getSubtotal(invoice);
	}

	public Integer getQuantity(Invoice invoice) {
		return invoiceDao.getQuantity(invoice);
	}
	
	public Double getTotalTax(Invoice invoice) {
		return invoiceDao.getTotalTax(invoice);
	}
	
	public Integer getInvoiceProfit(Integer invoiceID) {
		return invoiceDao.getInvoiceProfit(invoiceID);
	}
}
