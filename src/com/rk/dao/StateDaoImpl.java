package com.rk.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rk.model.State;

@Repository("stateDao")
public class StateDaoImpl implements StateDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addState(State state) {
		sessionFactory.getCurrentSession().saveOrUpdate(state);
	}

	@SuppressWarnings("unchecked")
	public List<State> listStates() {
		return (List<State>) sessionFactory.getCurrentSession().createCriteria(State.class).list();
	}

	public State getState(String state) {
		return (State) sessionFactory.getCurrentSession().get(State.class, state);
	}

	public void deleteState(State state) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM State WHERE state = '" + state.getState() + "'").executeUpdate();
	}

}
