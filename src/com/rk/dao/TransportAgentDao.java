package com.rk.dao;

import java.util.List;

import com.rk.model.TransportAgent;

public interface TransportAgentDao {
	public void addTransportAgent(TransportAgent partyAgent);
	public List<TransportAgent> listTransportAgents();
	public TransportAgent getTransportAgent(Integer agentID);
	public void deleteTransportAgent(TransportAgent partyAgent);
	public void addContact(TransportAgent partyAgent, List<Long> contact);
	public List<Long> getContact(TransportAgent partyAgent);
	public void addEmail(TransportAgent partyAgent, List<String> email);
	public List<String> getEmail(TransportAgent partyAgent);
}
