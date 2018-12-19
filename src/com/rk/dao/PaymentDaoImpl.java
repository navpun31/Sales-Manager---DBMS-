package com.rk.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Payment;

@Repository("paymentDao")
public class PaymentDaoImpl implements PaymentDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addPayment(Payment payment) {
		sessionFactory.getCurrentSession().saveOrUpdate(payment);
	}

	@SuppressWarnings("unchecked")
	public List<Payment> listPayments() {
		return (List<Payment>) sessionFactory.getCurrentSession().createCriteria(Payment.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listModePayments() {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(""
				+ "SELECT P.paymentID, P.dateOfPay, P.amount, P.modeOfPay, P.type, M.mode "
				+ "FROM Payment as P, payMode as M WHERE P.type = M.type"
				+ "")
				.addScalar("P.paymentID", Hibernate.INTEGER)
				.addScalar("P.dateOfPay", Hibernate.DATE)
				.addScalar("P.amount", Hibernate.INTEGER)
				.addScalar("P.modeOfPay", Hibernate.STRING)
				.addScalar("P.type", Hibernate.STRING)
				.addScalar("M.mode", Hibernate.STRING).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listDebitPayments(String table, String colName, Integer id) {
		return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(""
				+ "SELECT P.paymentID, P.dateOfPay, P.amount, P.modeOfPay, P.type, M.mode "
				+ "FROM Payment as P, payMode as M, " + table + " as X "
				+ "WHERE P.type = M.type and P.paymentID = X.paymentID and X." + colName + " = " + id + " "
				+ "")
				.addScalar("P.paymentID", Hibernate.INTEGER)
				.addScalar("P.dateOfPay", Hibernate.DATE)
				.addScalar("P.amount", Hibernate.INTEGER)
				.addScalar("P.modeOfPay", Hibernate.STRING)
				.addScalar("P.type", Hibernate.STRING)
				.addScalar("M.mode", Hibernate.STRING).list();
	}
	
	public Payment getPayment(Integer paymentID) {
		return (Payment) sessionFactory.getCurrentSession().get(Payment.class, paymentID);
	}

	public void deletePayment(Payment payment) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Payment WHERE paymentID = " + payment.getPaymentID()).executeUpdate();
	}

	public void linkPayment(String table, String colName, Payment payment, Integer id) {
		sessionFactory.getCurrentSession().createSQLQuery(""
				+ "INSERT INTO " + table + " VALUES (" + payment.getPaymentID() + ", " + id + ")"
						+ "").executeUpdate();
	}
}
