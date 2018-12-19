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

import com.rk.bean.PartyAgentBean;
import com.rk.bean.UserBean;
import com.rk.model.PartyAgent;
import com.rk.service.PartyAgentService;

@Controller
public class PartyAgentController {
	@Autowired
	private PartyAgentService partyAgentService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new PartyAgentBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("partyAgent", null);
		model.addAttribute("partyAgents",  prepareListofBean(partyAgentService.listPartyAgents()));
	}
	
	@RequestMapping(value="/partyagent", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("partyagent");
	}

	@RequestMapping(value = "/partyagent/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  PartyAgentBean partyAgentBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			partyAgentService.deletePartyAgent(prepareModel(partyAgentBean));
		}
		catch(Exception e) {
			return new ModelAndView("partyagent","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/partyagent");
	}
	
	@RequestMapping(value = "/partyagent/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  PartyAgentBean partyAgentBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("partyAgent", prepareBean(partyAgentService.getPartyAgent(partyAgentBean.getAgentID())));
		model.put("action", "Edit");
		return new ModelAndView("partyagent", model);
	}
	
	@RequestMapping(value = "/partyagent/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") PartyAgentBean partyAgentBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("partyagent",model);
		}
		
		PartyAgent partyAgent = prepareModel(partyAgentBean);
		partyAgentService.addPartyAgent(partyAgent);
		partyAgentService.addContact(partyAgent, partyAgentBean.getContact());
		partyAgentService.addEmail(partyAgent, partyAgentBean.getEmail());
		return new ModelAndView("redirect:/partyagent");
	}
	
	public PartyAgent prepareModel(PartyAgentBean partyAgentBean){
		PartyAgent partyAgent = new PartyAgent();
		partyAgent.setAgentID(partyAgentBean.getAgentID());
		partyAgent.setName(partyAgentBean.getName());
		partyAgent.setPerPartyPay(partyAgentBean.getPerPartyPay());
		return partyAgent;
	}
	
	public List<PartyAgentBean> prepareListofBean(List<PartyAgent> partyAgents){
		List<PartyAgentBean> beans = new ArrayList<PartyAgentBean>();
		if(partyAgents != null && !partyAgents.isEmpty()){
			for(PartyAgent agent : partyAgents){
				beans.add(prepareBean(agent));
			}
		}
		return beans;
	}
	
	public PartyAgentBean prepareBean(PartyAgent partyAgent){
		PartyAgentBean bean = new PartyAgentBean();
		bean.setAgentID(partyAgent.getAgentID());
		bean.setName(partyAgent.getName());
		bean.setPerPartyPay(partyAgent.getPerPartyPay());
		bean.setContact(partyAgentService.getContact(partyAgent));
		bean.setEmail(partyAgentService.getEmail(partyAgent));
		return bean;
	}
}
