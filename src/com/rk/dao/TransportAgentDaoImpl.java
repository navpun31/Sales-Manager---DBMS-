package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.TransportAgent;

@Repository("transportAgentDao")
public class TransportAgentDaoImpl implements TransportAgentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addTransportAgent(TransportAgent transportAgent) {
		sessionFactory.getCurrentSession().saveOrUpdate(transportAgent);
	}

	@SuppressWarnings("unchecked")
	public List<TransportAgent> listTransportAgents() {
		return (List<TransportAgent>) sessionFactory.getCurrentSession().createCriteria(TransportAgent.class).list();
	}

	public TransportAgent getTransportAgent(Integer agentID) {
		return (TransportAgent) sessionFactory.getCurrentSession().get(TransportAgent.class, agentID);
	}

	public void deleteTransportAgent(TransportAgent transportAgent) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM TransportAgent WHERE agentID = " + transportAgent.getAgentID()).executeUpdate();
	}
	
	public void addContact(TransportAgent transportAgent, List<Long> contact) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM transportAgentContact WHERE transportAgentID = " + transportAgent.getAgentID()).executeUpdate();
		for(Long L : contact) {
			if(L == null) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO transportAgentContact VALUES(" + transportAgent.getAgentID() + ", " + L + ")").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(TransportAgent transportAgent) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM transportAgentContact WHERE transportAgentID = " + transportAgent.getAgentID()).list();
	}

	public void addEmail(TransportAgent transportAgent, List<String> email) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM transportAgentEmail WHERE transportAgentID = " + transportAgent.getAgentID()).executeUpdate();
		for(String S : email) {
			if(S == null || S.isEmpty()) continue;
			sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO transportAgentEmail VALUES(" + transportAgent.getAgentID() + ", '" + S + "')").executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getEmail(TransportAgent transportAgent) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT email FROM transportAgentEmail WHERE transportAgentID = " + transportAgent.getAgentID()).list();
	}

}
