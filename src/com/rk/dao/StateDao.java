package com.rk.dao;

import java.util.List;

import com.rk.model.State;

public interface StateDao {
	public void addState(State state);
	public List<State> listStates();
	public State getState(String state);
	public void deleteState(State state);
}
