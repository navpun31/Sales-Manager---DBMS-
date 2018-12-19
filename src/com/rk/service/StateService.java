package com.rk.service;

import java.util.List;

import com.rk.model.State;

public interface StateService {
	public void addState(State state);
	public List<State> listStates();
	public State getState(String state);
	public void deleteState(State state);
}
