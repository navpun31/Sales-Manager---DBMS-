package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.PartyAgentDao;
import com.rk.model.PartyAgent;

@Service("partyAgentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PartyAgentServiceImpl implements PartyAgentService {
	@Autowired
	private PartyAgentDao partyAgentDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addPartyAgent(PartyAgent partyAgent) {
		return partyAgentDao.addPartyAgent(partyAgent);
	}

	public List<PartyAgent> listPartyAgents() {
		return partyAgentDao.listPartyAgents();
	}

	public PartyAgent getPartyAgent(Integer agentID) {
		return partyAgentDao.getPartyAgent(agentID);
	}

	public boolean deletePartyAgent(PartyAgent partyAgent) {
		return partyAgentDao.deletePartyAgent(partyAgent);
	}

	public boolean addContact(PartyAgent partyAgent, List<Long> contact) {
		return partyAgentDao.addContact(partyAgent, contact);
	}

	public List<Long> getContact(PartyAgent partyAgent) {
		return partyAgentDao.getContact(partyAgent);
	}

	public boolean addEmail(PartyAgent partyAgent, List<String> email) {
		return partyAgentDao.addEmail(partyAgent, email);
	}

	public List<String> getEmail(PartyAgent partyAgent) {
		return partyAgentDao.getEmail(partyAgent);
	}

}
