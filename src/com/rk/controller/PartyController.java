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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.*;
import com.rk.model.*;
import com.rk.service.*;

@Controller
public class PartyController {
	@Autowired
	private PartyAgentService partyAgentService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InvoiceEntryService invoiceEntryService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private ReturnEntryService returnEntryService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CreditNoteService creditNoteService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new PartyBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("party", null);
		model.addAttribute("parties",  prepareListofBean(partyService.listParties()));
		model.addAttribute("partyAgents", partyAgentService.listPartyAgents());
		model.addAttribute("cities", cityService.listCities());
	}
	
	@RequestMapping(value="/party", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("party");
	}

	@RequestMapping(value = "/party/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  PartyBean partyBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			partyService.deleteParty(prepareModel(partyBean));
		}
		catch(Exception e) {
			return new ModelAndView("party","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/party");
	}
	
	@RequestMapping(value = "/party/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  PartyBean partyBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("party", prepareBean(partyService.getParty(partyBean.getGstin())));
		model.put("action", "Edit");
		return new ModelAndView("party", model);
	}
	
	@RequestMapping(value = "/party/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") PartyBean partyBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("party",model);
		}
		if(partyBean.getAgentID() == -1) {
			partyBean.setAgentID(null);
		}
		
		Party party = prepareModel(partyBean);
		partyService.addParty(party);
		partyService.addContact(party, partyBean.getContact());
		partyService.addEmail(party, partyBean.getEmail());
		return new ModelAndView("redirect:/party");
	}
	
	@RequestMapping(value = "/party/ratechart/{partyID}", method = RequestMethod.GET)
	public ModelAndView ratechart(HttpSession session,
			@PathVariable("partyID") String partyID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		List<Object[]> ratechart = partyService.getRateList(partyID);
		model.put("ratechart", ratechart);
		return new ModelAndView("ratechart",model);
	}
	
	@RequestMapping(value = "/party/bills/{partyID}", method = RequestMethod.GET)
	public ModelAndView bills(HttpSession session,
			@PathVariable("partyID") String partyID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("invoices", prepareListofInvoiceBean(invoiceService.listInvoices(partyID)));
		model.put("creditNotes", prepareListofCreditBean(creditNoteService.listCreditNotes(partyID)));
		return new ModelAndView("partybills",model);
	}
	
	@RequestMapping(value = "/partyagent/parties/{agentID}", method = RequestMethod.GET)
	public ModelAndView agentParties(HttpSession session,
			@PathVariable("agentID") Integer agentID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("parties", prepareListofBean(partyService.listAgentParties(agentID)));
		return new ModelAndView("agentParties",model);
	}
	
	private Party prepareModel(PartyBean partyBean){
		Party party = new Party();
		party.setGstin(partyBean.getGstin());
		party.setFirmName(partyBean.getFirmName());
		if(partyBean.getAddress() != null) {
			party.setAddressLine1(partyBean.getAddress().getAddressLine1());
			party.setAddressLine2(partyBean.getAddress().getAddressLine2());
			party.setPin(partyBean.getAddress().getPin());
			party.setCity(partyBean.getAddress().getCity());
		}
		party.setAgentID(partyBean.getAgentID());
		return party;
	}
	
	private List<PartyBean> prepareListofBean(List<Party> parties){
		List<PartyBean> beans = null;
		if(parties != null && !parties.isEmpty()){
			beans = new ArrayList<PartyBean>();
			for(Party party : parties){
				beans.add(prepareBean(party));
			}
		}
		return beans;
	}
	
	private PartyBean prepareBean(Party party){
		PartyBean bean = new PartyBean();
		bean.setGstin(party.getGstin());
		bean.setFirmName(party.getFirmName());
		
		Address address = new Address();
		address.setAddressLine1(party.getAddressLine1());
		address.setAddressLine2(party.getAddressLine2());
		address.setPin(party.getPin());
		address.setCity(party.getCity());
		City city = cityService.getCity(party.getCity());
		State state = stateService.getState(city.getState());
		address.setStateName(state.getState());
		address.setStateCode(state.getStateCode());
		address.setCountry(state.getCountry());
		
		bean.setAddress(address);
		bean.setAgentID(party.getAgentID());
		if(party.getAgentID() != null) {
			bean.setPartyAgent(partyAgentService.getPartyAgent(party.getAgentID()));
		}
		else {
			bean.setPartyAgent(null);
		}
		bean.setContact(partyService.getContact(party));
		bean.setEmail(partyService.getEmail(party));
		return bean;
	}
	
	/* Invoice */
	public List<InvoiceBean> prepareListofInvoiceBean(List<Invoice> invoices){
		List<InvoiceBean> beans = null;
		if(invoices != null && !invoices.isEmpty()){
			beans = new ArrayList<InvoiceBean>();
			for(Invoice invoice : invoices){
				beans.add(prepareBean(invoice));
			}
		}
		return beans;
	}
	
	public InvoiceBean prepareBean(Invoice invoice){
		InvoiceBean bean = new InvoiceBean();
		bean.setInvoiceID(invoice.getInvoiceID());
		bean.setFreight(invoice.getFreight());
		bean.setPaymentID(invoice.getPaymentID());
		bean.setDateOfIssue(invoice.getDateOfIssue());
		bean.setDiscount(invoice.getDiscount());
		bean.setEwayNo(invoice.getEwayNo());
		bean.setLorryReceiptNo(invoice.getLorryReceiptNo());
		bean.setPartyID(invoice.getPartyID());
		bean.setVehicleID(invoice.getVehicleID());
		bean.setTax(invoiceService.getTax(invoice));
		bean.setTotalTax(invoiceService.getTotalTax(invoice));
		bean.setQuantity(invoiceService.getQuantity(invoice));
		bean.setSubtotal(invoiceService.getSubtotal(invoice));

		if(invoice.getPartyID() != null) {
			bean.setParty(partyService.getParty(invoice.getPartyID()));
		}
		else {
			bean.setParty(null);
		}
		
		if(invoice.getPaymentID() != null) {
			bean.setPayment(paymentService.getPayment(invoice.getPaymentID()));
		}
		else {
			bean.setPayment(null);
		}
		
		if(invoice.getVehicleID() != null) {
			bean.setVehicle(vehicleService.getVehicle(invoice.getVehicleID()));
		}
		else {
			bean.setVehicle(null);
		}
		
		return bean;
	}
	
	/* Credit Note */
	public List<CreditNoteBean> prepareListofCreditBean(List<CreditNote> creditNotes){
		List<CreditNoteBean> beans = null;
		if(creditNotes != null && !creditNotes.isEmpty()){
			beans = new ArrayList<CreditNoteBean>();
			for(CreditNote creditNote : creditNotes){
				beans.add(prepareBean(creditNote));
			}
		}
		return beans;
	}
	
	public CreditNoteBean prepareBean(CreditNote creditNote){
		CreditNoteBean bean = new CreditNoteBean();
		bean.setCreditNoteNo(creditNote.getCreditNoteNo());
		bean.setDateOfReturn(creditNote.getDateOfReturn());
		bean.setPartyID(creditNote.getPartyID());
		bean.setPaymentID(creditNote.getPaymentID());
		
		List<ReturnEntryBean> returnBeans = 
				prepareListofEntryBean(returnEntryService.listReturnEntries(creditNote.getCreditNoteNo()));
		
		Integer quantity = new Integer(0);
		Double total = new Double(0.0);
		
		if(returnBeans != null && !returnBeans.isEmpty()) {
			for(ReturnEntryBean returnBean : returnBeans) {
				quantity += returnBean.getQuantity();
				total += returnBean.getTotal();
			}
		}
		
		bean.setQuantity(quantity);
		bean.setTotal(total);
		
		if(creditNote.getPartyID() != null) {
			bean.setParty(partyService.getParty(creditNote.getPartyID()));
		}
		else {
			bean.setParty(null);
		}
		
		if(creditNote.getPaymentID() != null) {
			bean.setPayment(paymentService.getPayment(creditNote.getPaymentID()));
		}
		else {
			bean.setPayment(null);
		}
		
		return bean;
	}
	
	private List<ReturnEntryBean> prepareListofEntryBean(List<ReturnEntry> returnEntries){
		List<ReturnEntryBean> beans = null;
		if(returnEntries != null && !returnEntries.isEmpty()){
			beans = new ArrayList<ReturnEntryBean>();
			for(ReturnEntry returnEntry : returnEntries){
				beans.add(prepareEntryBean(returnEntry));
			}
		}
		return beans;
	}
	
	private ReturnEntryBean prepareEntryBean(ReturnEntry returnEntry){
		ReturnEntryBean bean = new ReturnEntryBean();
		
		ReturnEntryKey key = new ReturnEntryKey();
		key.setCreditNoteNo(returnEntry.getCreditNoteNo());
		key.setProductID(returnEntry.getProductID());
		key.setInvoiceID(returnEntry.getInvoiceID());
		bean.setKey(key);
		
		InvoiceEntryKey inKey = new InvoiceEntryKey(key.getInvoiceID(),key.getProductID());
		InvoiceEntry invoiceEntry = invoiceEntryService.getInvoiceEntry(inKey);
		bean.setPrice(invoiceEntry.getPrice());
		bean.setTax(invoiceService.getTotalTax(invoiceService.getInvoice(key.getInvoiceID())));
		bean.setQuantity(returnEntry.getQuantity());
		bean.setProduct(productService.getProduct(returnEntry.getProductID()));
		return bean;
	}
}
