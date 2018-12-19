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

import com.rk.bean.TransportAgentBean;
import com.rk.bean.UserBean;
import com.rk.model.TransportAgent;
import com.rk.service.TransportAgentService;

@Controller
public class TransportAgentController {
	@Autowired
	private TransportAgentService transportAgentService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new TransportAgentBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("transportAgent", null);
		model.addAttribute("transportAgents",  prepareListofBean(transportAgentService.listTransportAgents()));
	}
	
	@RequestMapping(value="/transportagent", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("transportagent");
	}

	@RequestMapping(value = "/transportagent/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  TransportAgentBean transportAgentBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			transportAgentService.deleteTransportAgent(prepareModel(transportAgentBean));
		}
		catch(Exception e) {
			return new ModelAndView("transportagent","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/transportagent");
	}
	
	@RequestMapping(value = "/transportagent/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  TransportAgentBean transportAgentBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("transportAgent", prepareBean(transportAgentService.getTransportAgent(transportAgentBean.getAgentID())));
		model.put("action", "Edit");
		return new ModelAndView("transportagent", model);
	}
	
	@RequestMapping(value = "/transportagent/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") TransportAgentBean transportAgentBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("transportagent",model);
		}
		
		TransportAgent transportAgent = prepareModel(transportAgentBean);
		transportAgentService.addTransportAgent(transportAgent);
		transportAgentService.addContact(transportAgent, transportAgentBean.getContact());
		transportAgentService.addEmail(transportAgent, transportAgentBean.getEmail());
		return new ModelAndView("redirect:/transportagent");
	}
	
	public TransportAgent prepareModel(TransportAgentBean transportAgentBean){
		TransportAgent transportAgent = new TransportAgent();
		transportAgent.setAgentID(transportAgentBean.getAgentID());
		transportAgent.setCompanyName(transportAgentBean.getCompanyName());
		transportAgent.setPerDeliveryPay(transportAgentBean.getPerDeliveryPay());
		return transportAgent;
	}
	
	public List<TransportAgentBean> prepareListofBean(List<TransportAgent> transportAgents){
		List<TransportAgentBean> beans = new ArrayList<TransportAgentBean>();
		if(transportAgents != null && !transportAgents.isEmpty()){
			for(TransportAgent agent : transportAgents){
				beans.add(prepareBean(agent));
			}
		}
		return beans;
	}
	
	public TransportAgentBean prepareBean(TransportAgent transportAgent){
		TransportAgentBean bean = new TransportAgentBean();
		bean.setAgentID(transportAgent.getAgentID());
		bean.setCompanyName(transportAgent.getCompanyName());
		bean.setPerDeliveryPay(transportAgent.getPerDeliveryPay());
		bean.setContact(transportAgentService.getContact(transportAgent));
		bean.setEmail(transportAgentService.getEmail(transportAgent));
		return bean;
	}
}
