package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.TransportAgentDao;
import com.rk.model.TransportAgent;

@Service("transportAgentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransportAgentServiceImpl implements TransportAgentService {
	@Autowired
	private TransportAgentDao transportAgentDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTransportAgent(TransportAgent transportAgent) {
		transportAgentDao.addTransportAgent(transportAgent);
	}

	public List<TransportAgent> listTransportAgents() {
		return transportAgentDao.listTransportAgents();
	}

	public TransportAgent getTransportAgent(Integer agentID) {
		return transportAgentDao.getTransportAgent(agentID);
	}

	public void deleteTransportAgent(TransportAgent transportAgent) {
		transportAgentDao.deleteTransportAgent(transportAgent);
	}

	public void addContact(TransportAgent transportAgent, List<Long> contact) {
		transportAgentDao.addContact(transportAgent, contact);
	}

	public List<Long> getContact(TransportAgent transportAgent) {
		return transportAgentDao.getContact(transportAgent);
	}

	public void addEmail(TransportAgent transportAgent, List<String> email) {
		transportAgentDao.addEmail(transportAgent, email);
	}

	public List<String> getEmail(TransportAgent transportAgent) {
		return transportAgentDao.getEmail(transportAgent);
	}

}
