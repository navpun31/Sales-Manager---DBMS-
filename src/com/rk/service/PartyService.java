package com.rk.service;

import java.util.List;
import com.rk.model.*;

public interface PartyService {
	public void addParty(Party party);
	public List<Party> listParties();
	public List<Party> listAgentParties(Integer agentID);
	public Party getParty(String gstin);
	public void deleteParty(Party party);
	public void addContact(Party party, List<Long> contact);
	public List<Long> getContact(Party party);
	public void addEmail(Party party, List<String> email);
	public List<String> getEmail(Party party);	
	public List<Object[]> getRateList(String partyID);
	public List<Object[]> listPartyProfits();
}
