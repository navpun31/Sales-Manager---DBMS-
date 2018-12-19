package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.PartyAgent;

@Repository("partyAgentDao")
public class PartyAgentDaoImpl implements PartyAgentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addPartyAgent(PartyAgent partyAgent) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(partyAgent);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<PartyAgent> listPartyAgents() {
		return (List<PartyAgent>) sessionFactory.getCurrentSession().createCriteria(PartyAgent.class).list();
	}

	public PartyAgent getPartyAgent(Integer agentID) {
		return (PartyAgent) sessionFactory.getCurrentSession().get(PartyAgent.class, agentID);
	}

	public boolean deletePartyAgent(PartyAgent partyAgent) {
		try {
			sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM PartyAgent WHERE agentID = " + partyAgent.getAgentID()).executeUpdate();
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addContact(PartyAgent partyAgent, List<Long> contact) {
		try {
			sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM partyAgentContact WHERE partyAgentID = " + partyAgent.getAgentID()).executeUpdate();
			for(Long L : contact) {
				if(L == null) continue;
				sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO partyAgentContact VALUES(" + partyAgent.getAgentID() + ", " + L + ")").executeUpdate();
			}
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getContact(PartyAgent partyAgent) {
		return (List<Long>) sessionFactory.getCurrentSession().createSQLQuery("SELECT contact FROM partyAgentContact WHERE partyAgentID = " + partyAgent.getAgentID()).list();
	}

	public boolean addEmail(PartyAgent partyAgent, List<String> email) {
		try {
			sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM partyAgentEmail WHERE partyAgentID = " + partyAgent.getAgentID()).executeUpdate();
			for(String S : email) {
				if(S == null || S.isEmpty()) continue;
				sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO partyAgentEmail VALUES(" + partyAgent.getAgentID() + ", '" + S + "')").executeUpdate();
			}
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<String> getEmail(PartyAgent partyAgent) {
		return (List<String>) sessionFactory.getCurrentSession().createSQLQuery("SELECT email FROM partyAgentEmail WHERE partyAgentID = " + partyAgent.getAgentID()).list();
	}

}
