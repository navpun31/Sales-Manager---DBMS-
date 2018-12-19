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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rk.bean.ClientBean;
import com.rk.bean.InvoiceBean;
import com.rk.bean.InvoiceEntryBean;
import com.rk.bean.InvoiceEntryKey;
import com.rk.bean.UserBean;
import com.rk.model.Invoice;
import com.rk.model.InvoiceEntry;
import com.rk.model.Party;
import com.rk.model.Tax;
import com.rk.service.CityService;
import com.rk.service.InvoiceEntryService;
import com.rk.service.InvoiceService;
import com.rk.service.PartyService;
import com.rk.service.PaymentService;
import com.rk.service.ProductService;
import com.rk.service.StateService;
import com.rk.service.TaxService;
import com.rk.service.TransportAgentService;
import com.rk.service.VehicleService;

@Controller
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InvoiceEntryService invoiceEntryService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private TransportAgentService transportAgentService;
	@Autowired
	private TaxService taxService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private PaymentService paymentService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new InvoiceBean());
		model.addAttribute("commandEntry", new InvoiceEntryBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("invoice", null);
		model.addAttribute("invoiceEntry", null);
		model.addAttribute("invoices",  prepareListofBean(invoiceService.listInvoices()));
		model.addAttribute("parties",  partyService.listParties());
		model.addAttribute("vehicles",  vehicleService.listVehicles());
		model.addAttribute("taxes",  taxService.listTaxes());
	}
	
	/* Invoice */
	@RequestMapping(value="/invoice", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("invoice");
	}

	@RequestMapping(value = "/invoice/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  InvoiceBean invoiceBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		InvoiceBean bean = prepareBean(invoiceService.getInvoice(invoiceBean.getInvoiceID()));
		if(bean.getClear()) {
			return new ModelAndView("redirect:/invoice");
		}
		try {
			invoiceService.deleteInvoice(prepareModel(invoiceBean));
		}
		catch(Exception e) {
			return new ModelAndView("invoice","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/invoice");
	}
	
	@RequestMapping(value = "/invoice/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  InvoiceBean invoiceBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		InvoiceBean bean = prepareBean(invoiceService.getInvoice(invoiceBean.getInvoiceID()));
		if(bean.getClear()) {
			return new ModelAndView("redirect:/invoice");
		}
		model.put("action", "Edit");
		model.put("invoice", bean);
		return new ModelAndView("invoice", model);
	}
	
	@RequestMapping(value = "/invoice/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") InvoiceBean invoiceBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("invoice",model);
		}
		
		invoiceBean.setPaymentID(null);
		Invoice invoice = prepareModel(invoiceBean);
		invoiceService.addInvoice(invoice);
		invoiceService.addTax(invoice, invoiceBean.getTax());
		return new ModelAndView("redirect:/invoice");
	}
	
	@RequestMapping(value = "/invoice/print/{invoiceID}/{type}", method = RequestMethod.GET)
	public ModelAndView print(HttpSession session,
			@PathVariable("invoiceID") Integer invoiceID,
			@PathVariable("type") String type) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals("original") && !type.equals("duplicate")) {
			return new ModelAndView("redirect:/invoice");
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		InvoiceBean bean = prepareBean(invoiceService.getInvoice(invoiceID));
		model.put("invoice", bean);
		Party party = partyService.getParty(bean.getPartyID());
		model.put("party", party);
		model.put("state", stateService.getState(
				cityService.getCity(party.getCity()).getState()
				));
		model.put("transport", 
				transportAgentService.getTransportAgent(
				vehicleService.getVehicle(bean.getVehicleID()).getAgentID()
				));
		model.put("contact", partyService.getContact(party));
		model.put("email", partyService.getEmail(party));
		model.put("type", type);
		model.put("entries", prepareListofEntryBean(invoiceEntryService.listInvoiceEntries(invoiceID)));
		List<Tax> taxes = taxService.listTaxes(bean.getTax());
		model.put("tax", taxes);
		model.put("span",taxes.size()+2);
		ClientBean client = (ClientBean)session.getAttribute("client");
		model.put("firmName",client.getFirmName().toUpperCase());
		return new ModelAndView("invoicePrint", model);
	}
	
	/* Invoice Entry */
	@RequestMapping(value = "/invoice/addentries/{invoiceID}", method = RequestMethod.GET)
	public ModelAndView listInvoiceEntries(@PathVariable("invoiceID") Integer invoiceID, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Invoice invoice = invoiceService.getInvoice(invoiceID);
		if(invoice == null) {
			return new ModelAndView("redirect:/invoice");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		InvoiceBean invoiceBean = prepareBean(invoice);
		model.put("invoice", invoiceBean);
		model.put("entries", prepareListofEntryBean(invoiceEntryService.listInvoiceEntries(invoiceID)));
		model.put("products", productService.listProducts());
		return new ModelAndView("addentries",model);
	}

	@RequestMapping(value = "/invoice/addentries/{invoiceID}/add", method = RequestMethod.POST)
	public ModelAndView saveInvoiceEntry(HttpSession session,
			@PathVariable("invoiceID") Integer invoiceID,
			@Valid @ModelAttribute("commandEntry") InvoiceEntryBean invoiceEntryBean, 
			BindingResult result) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String,Object> model = new HashMap<String,Object>();
			InvoiceBean invoiceBean = prepareBean(invoiceService.getInvoice(invoiceID));
			model.put("invoice", invoiceBean);
			model.put("entries", prepareListofEntryBean(invoiceEntryService.listInvoiceEntries(invoiceID)));
			model.put("products", productService.listProducts());
			model.put("errorMessage", "Enter valid details.");
			return new ModelAndView("addentries",model);
		}
//		invoiceEntryBean.setInvoiceID(invoiceID);
		InvoiceEntry invoiceEntry = prepareEntryModel(invoiceEntryBean);	
		try {
			invoiceEntryService.addInvoiceEntry(invoiceEntry);
		}
		catch(Exception e) {
			Map<String,Object> model = new HashMap<String,Object>();
			InvoiceBean invoiceBean = prepareBean(invoiceService.getInvoice(invoiceID));
			model.put("invoice", invoiceBean);
			model.put("entries", prepareListofEntryBean(invoiceEntryService.listInvoiceEntries(invoiceID)));
			model.put("products", productService.listProducts());
			model.put("errorMessage", "Enter valid Quantity/Price.");
			return new ModelAndView("addentries",model);
		}
		return new ModelAndView("redirect:/invoice/addentries/" + invoiceID);
	}
	
	@RequestMapping(value = "/invoice/addentries/{invoiceID}/edit", method = RequestMethod.GET)
	public ModelAndView editInvoiceEntry(HttpSession session,
			@PathVariable("invoiceID") Integer invoiceID,
			@RequestParam("productID") String productID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		InvoiceEntryBean invoiceEntryBean = prepareEntryBean(invoiceEntryService.getInvoiceEntry(new InvoiceEntryKey(invoiceID,productID)));
		InvoiceBean invoiceBean = prepareBean(invoiceService.getInvoice(invoiceID));
		model.put("invoice", invoiceBean);
		model.put("entries", prepareListofEntryBean(invoiceEntryService.listInvoiceEntries(invoiceID)));
		model.put("products", productService.listProducts());
		model.put("invoiceEntry", invoiceEntryBean);
		return new ModelAndView("addentries",model);
	}
	
	@RequestMapping(value = "/invoice/addentries/{invoiceID}/delete", method = RequestMethod.GET)
	public ModelAndView deleteInvoiceEntry(HttpSession session,
			@PathVariable("invoiceID") Integer invoiceID,
			@RequestParam("productID") String productID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			invoiceEntryService.deleteInvoiceEntry(
					invoiceEntryService.getInvoiceEntry(new InvoiceEntryKey(invoiceID,productID)));
		}
		catch(Exception e) {
			return new ModelAndView("color","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/invoice/addentries/" + invoiceID);
	}
	
	/* Invoice */
	public Invoice prepareModel(InvoiceBean invoiceBean){
		Invoice invoice = new Invoice();
		invoice.setInvoiceID(invoiceBean.getInvoiceID());
		invoice.setFreight(invoiceBean.getFreight());
		invoice.setPaymentID(invoiceBean.getPaymentID());
		invoice.setDateOfIssue(invoiceBean.getDateOfIssue());
		invoice.setDiscount(invoiceBean.getDiscount());
		invoice.setEwayNo(invoiceBean.getEwayNo());
		invoice.setLorryReceiptNo(invoiceBean.getLorryReceiptNo());
		invoice.setPartyID(invoiceBean.getPartyID());
		invoice.setVehicleID(invoiceBean.getVehicleID());
		return invoice;
	}
	
	public List<InvoiceBean> prepareListofBean(List<Invoice> invoices){
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

		bean.setProfit(invoiceService.getInvoiceProfit(invoice.getInvoiceID()));
		return bean;
	}
	
	/* Invoice Entry */
	private InvoiceEntry prepareEntryModel(InvoiceEntryBean invoiceEntryBean){
		InvoiceEntry invoiceEntry = new InvoiceEntry();
		invoiceEntry.setInvoiceID(invoiceEntryBean.getInvoiceID());
		invoiceEntry.setPrice(invoiceEntryBean.getPrice());
		invoiceEntry.setQuantity(invoiceEntryBean.getQuantity());
		invoiceEntry.setProductID(invoiceEntryBean.getProductID());
		return invoiceEntry;
	}
	
	private List<InvoiceEntryBean> prepareListofEntryBean(List<InvoiceEntry> invoiceEntries){
		List<InvoiceEntryBean> beans = null;
		if(invoiceEntries != null && !invoiceEntries.isEmpty()){
			beans = new ArrayList<InvoiceEntryBean>();
			for(InvoiceEntry invoiceEntry : invoiceEntries){
				beans.add(prepareEntryBean(invoiceEntry));
			}
		}
		return beans;
	}
	
	private InvoiceEntryBean prepareEntryBean(InvoiceEntry invoiceEntry){
		InvoiceEntryBean bean = new InvoiceEntryBean();
		InvoiceEntryKey key = new InvoiceEntryKey();
		key.setInvoiceID(invoiceEntry.getInvoiceID());
		key.setProductID(invoiceEntry.getProductID());
		bean.setKey(key);
		bean.setPrice(invoiceEntry.getPrice());
		bean.setQuantity(invoiceEntry.getQuantity());
		bean.setProduct(productService.getProduct(invoiceEntry.getProductID()));
		return bean;
	}
}
