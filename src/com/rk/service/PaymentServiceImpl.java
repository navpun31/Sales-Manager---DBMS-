package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.PaymentDao;
import com.rk.model.Payment;

@Service("paymentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addPayment(Payment payment) {
		paymentDao.addPayment(payment);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void linkPayment(String table, String colName, Payment payment, Integer id) {
		paymentDao.linkPayment(table, colName, payment, id);
	}
	
	public List<Payment> listPayments() {
		return paymentDao.listPayments();
	}
	
	public List<Object[]> listDebitPayments(String table, String colName, Integer id){
		return paymentDao.listDebitPayments(table, colName, id);
	}

	public List<Object[]> listModePayments() {
		return paymentDao.listModePayments();
	}

	public Payment getPayment(Integer paymentID) {
		return paymentDao.getPayment(paymentID);
	}

	public void deletePayment(Payment payment) {
		paymentDao.deletePayment(payment);
	}
}
