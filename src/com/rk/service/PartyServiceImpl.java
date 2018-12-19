package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.*;
import com.rk.model.*;

@Service("partyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PartyServiceImpl implements PartyService {
	@Autowired
	private PartyDao partyDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addParty(Party party) {
		partyDao.addParty(party);
	}
	
	public List<Party> listParties() {
		return partyDao.listParties();
	}
	
	public List<Party> listAgentParties(Integer agentID) {
		return partyDao.listAgentParties(agentID);
	}
	
	public Party getParty(String gstin) {
		return partyDao.getParty(gstin);
	}
	
	public void deleteParty(Party party) {
		partyDao.deleteParty(party);
	}
	
	public void addContact(Party party, List<Long> contact) {
		partyDao.addContact(party, contact);
	}
	
	public List<Long> getContact(Party party) {
		return partyDao.getContact(party);
	}
	
	public void addEmail(Party party, List<String> email) {
		partyDao.addEmail(party, email);
	}
	
	public List<String> getEmail(Party party) {
		return partyDao.getEmail(party);
	}
	
	public List<Object[]> getRateList(String partyID) {
		return partyDao.getRateList(partyID);
	}
	
	public List<Object[]> listPartyProfits() {
		return partyDao.listPartyProfits();
	}

}
