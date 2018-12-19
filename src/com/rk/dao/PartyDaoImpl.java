package com.rk.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.*;

@Repository("partyDao")
public class PartyDaoImpl implements PartyDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addParty(Party party) {
		sessionFactory.getCurrentSession().saveOrUpdate(party);
	}

	@SuppressWarnings("unchecked")
	public List<Party> listParties() {
		return (List<Party>) sessionFactory.getCurrentSession().createCriteria(Party.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> listAgentParties(Integer agentID) {
		return (List<Party>) sessionFactory.getCurrentSession().createQuery("From Party where agentID = " + agentID).list();
	}

	public Party getParty(String gstin) {
		return (Party) sessionFactory.getCurrentSession().get(Party.class, gstin);
	}

	public void deleteParty(Party party) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Party WHERE gstin = '" + party.getGstin() + "'").executeUpdate();
	}

	public void addContact(Party party, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM partyContact WHERE partyID = '" + party.getGstin() + "'").executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO partyContact VALUES('" + party.getGstin() + "', " + L + ")").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(Party party) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM partyContact WHERE partyID = '" + party.getGstin() + "'").list();
	}

	public void addEmail(Party party, List<String> email) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM partyEmail WHERE partyID = '" + party.getGstin() + "' ").executeUpdate();
		for(String S : email) {
			if(S == null || S.isEmpty()) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO partyEmail VALUES('" + party.getGstin() + "', '" + S + "')").executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getEmail(Party party) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT email FROM partyEmail WHERE partyID = '" + party.getGstin() + "'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRateList(String partyID) {
		List<Object[]> rows = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(""
				+ "SELECT P.productID, P.name, P.cost, R.rate FROM (Product as P LEFT OUTER JOIN "
					+ "(SELECT MAX(price) as rate, productID FROM InvoiceEntry WHERE "
					+ "invoiceID IN (SELECT invoiceID FROM Invoice WHERE partyID = '" + partyID + "') GROUP BY productID) as R "
						+ "ON P.productID = R.productID)"
							+ "").list();
		if(rows == null) return null;
		if(rows.isEmpty())	return new ArrayList<Object[]>();
		return rows;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listPartyProfits() {
		List<Object[]> rows = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(""
				+ "SELECT concat(concat(concat(PP.firmName,' (') ,PP.gstin),')') as partyName, X.profit FROM Party as PP, " + 
				"(SELECT I.partyID as partyID, SUM(PROF.sell-PROF.cost) as profit " + 
				"FROM Invoice as I, " + 
				"(SELECT SUM(price*quantity) as sell, " + 
				"( SELECT SUM(P.cost*IE.quantity) FROM Product as P, InvoiceEntry as IE " + 
				"	WHERE P.productID = IE.productID and invoiceID = INVOICEID ) as cost, " + 
				"invoiceID as INVOICEID FROM InvoiceEntry as I GROUP BY invoiceID) as PROF " + 
				"WHERE I.invoiceID = PROF.INVOICEID " + 
				"GROUP BY partyID) as X " + 
				"WHERE PP.gstin = X.partyID").list();
		if(rows == null) return null;
		if(rows.isEmpty())	return new ArrayList<Object[]>();
		return rows;
	}
	
	
}
