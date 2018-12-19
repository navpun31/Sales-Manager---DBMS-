package com.rk.service;

import java.util.List;

import com.rk.model.PartyAgent;

public interface PartyAgentService {
	public boolean addPartyAgent(PartyAgent partyAgent);
	public List<PartyAgent> listPartyAgents();
	public PartyAgent getPartyAgent(Integer agentID);
	public boolean deletePartyAgent(PartyAgent partyAgent);
	public boolean addContact(PartyAgent partyAgent, List<Long> contact);
	public List<Long> getContact(PartyAgent partyAgent);
	public boolean addEmail(PartyAgent partyAgent, List<String> email);
	public List<String> getEmail(PartyAgent partyAgent);
}
