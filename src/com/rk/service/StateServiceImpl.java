package com.rk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rk.dao.StateDao;
import com.rk.model.State;

@Service("stateService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StateServiceImpl implements StateService {
	@Autowired
	private StateDao stateDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addState(State state) {
		stateDao.addState(state);
	}

	public List<State> listStates() {
		return stateDao.listStates();
	}

	public State getState(String state) {
		return stateDao.getState(state);
	}

	public void deleteState(State state) {
		stateDao.deleteState(state);
	}
}
