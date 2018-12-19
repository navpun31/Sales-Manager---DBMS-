package com.rk.service;

import java.util.List;

import com.rk.model.Invoice;

public interface InvoiceService {
	public void addInvoice(Invoice invoice);
	public List<Invoice> listInvoices();
	public List<Invoice> listInvoices(String partyID);
	public List<Invoice> listUnpaidInvoices(String partyID);
	public Invoice getInvoice(Integer invoiceID);
	public void deleteInvoice(Invoice invoice);
	public void addTax(Invoice invoice, List<String> taxes);
	public List<String> getTax(Invoice invoice);
	public Integer getSubtotal(Invoice invoice);
	public Integer getQuantity(Invoice invoice);
	public Double getTotalTax(Invoice invoice);
	public Integer getInvoiceProfit(Integer invoiceID);
}
