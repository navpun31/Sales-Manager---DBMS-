package com.rk.dao;

import com.rk.model.Client;

public interface ClientDao {
	public void addClient(Client client);
	public Client getClient();
	public void deleteClient();
}
