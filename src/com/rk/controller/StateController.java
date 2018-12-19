package com.rk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.StateBean;
import com.rk.bean.UserBean;
import com.rk.model.State;
import com.rk.service.StateService;

@Controller
public class StateController {
	@Autowired
	private StateService stateService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new StateBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("state", null);
		model.addAttribute("states",  prepareListofBean(stateService.listStates()));
	}
	
	@RequestMapping(value="/state", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("state");
	}

	@RequestMapping(value = "/state/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  StateBean stateBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			stateService.deleteState(prepareModel(stateBean));
		}
		catch(Exception e) {
			return new ModelAndView("state","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/state");
	}
	
	@RequestMapping(value = "/state/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  StateBean stateBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("state", prepareBean(stateService.getState(stateBean.getState())));
		model.put("action", "Edit");
		return new ModelAndView("state", model);
	}
	
	@RequestMapping(value = "/state/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") StateBean stateBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("state",model);
		}
		
		State state = prepareModel(stateBean);
		stateService.addState(state);
		return new ModelAndView("redirect:/state");
	}
	
	private State prepareModel(StateBean stateBean){
		State state = new State();
		state.setState(stateBean.getState());
		state.setStateCode(stateBean.getStateCode());
		state.setCountry(stateBean.getCountry());
		return state;
	}
	
	private List<StateBean> prepareListofBean(List<State> states){
		List<StateBean> beans = null;
		if(states != null && !states.isEmpty()){
			beans = new ArrayList<StateBean>();
			for(State state : states){
				beans.add(prepareBean(state));
			}
		}
		return beans;
	}
	
	private StateBean prepareBean(State state){
		StateBean bean = new StateBean();
		bean.setState(state.getState());
		bean.setStateCode(state.getStateCode());
		bean.setCountry(state.getCountry());
		return bean;
	}
}
