package com.rk.service;

import java.util.List;

import com.rk.model.Payment;

public interface PaymentService {
	public void addPayment(Payment payment);
	public List<Payment> listPayments();
	public List<Object[]> listModePayments();
	public Payment getPayment(Integer paymentID);
	public void deletePayment(Payment payment);
	public void linkPayment(String table, String colName, Payment payment, Integer id);
	public List<Object[]> listDebitPayments(String table, String colName, Integer id);
}
