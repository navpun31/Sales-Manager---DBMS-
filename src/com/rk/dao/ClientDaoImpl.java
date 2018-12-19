package com.rk.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.Client;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addClient(Client client) {
		sessionFactory.getCurrentSession().saveOrUpdate(client);
	}

	public Client getClient() {
		return (Client) sessionFactory.getCurrentSession().get(Client.class, 0);
	}

	public void deleteClient() {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Client").executeUpdate();
	}

}
