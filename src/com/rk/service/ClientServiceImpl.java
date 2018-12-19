package com.rk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.ClientDao;
import com.rk.model.Client;

@Service("clientService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addClient(Client client) {
		clientDao.addClient(client);
	}

	public Client getClient() {
		return clientDao.getClient();
	}

	public void deleteClient() {
		clientDao.deleteClient();
	}
}
