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

import com.rk.bean.TaxBean;
import com.rk.bean.UserBean;
import com.rk.model.Tax;
import com.rk.service.TaxService;

@Controller
public class TaxController {
	@Autowired
	private TaxService taxService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new TaxBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("tax", null);
		model.addAttribute("taxes",  prepareListofBean(taxService.listTaxes()));
	}
	
	@RequestMapping(value="/tax", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("tax");
	}

	@RequestMapping(value = "/tax/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  TaxBean taxBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			taxService.deleteTax(prepareModel(taxBean));
		}
		catch(Exception e) {
			return new ModelAndView("tax","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/tax");
	}
	
	@RequestMapping(value = "/tax/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  TaxBean taxBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tax", prepareBean(taxService.getTax(taxBean.getTaxType())));
		model.put("action", "Edit");
		return new ModelAndView("tax", model);
	}
	
	@RequestMapping(value = "/tax/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") TaxBean taxBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("tax",model);
		}
		
		Tax tax = prepareModel(taxBean);
		taxService.addTax(tax);
		return new ModelAndView("redirect:/tax");
	}
	
	private Tax prepareModel(TaxBean taxBean){
		Tax tax = new Tax();
		tax.setTaxType(taxBean.getTaxType());
		tax.setPercent(taxBean.getPercent());
		return tax;
	}
	
	private List<TaxBean> prepareListofBean(List<Tax> taxes){
		List<TaxBean> beans = null;
		if(taxes != null && !taxes.isEmpty()){
			beans = new ArrayList<TaxBean>();
			for(Tax tax : taxes){
				beans.add(prepareBean(tax));
			}
		}
		return beans;
	}
	
	private TaxBean prepareBean(Tax tax){
		TaxBean bean = new TaxBean();
		bean.setTaxType(tax.getTaxType());
		bean.setPercent(tax.getPercent());
		return bean;
	}
}
