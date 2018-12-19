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
import com.rk.bean.CreditNoteBean;
import com.rk.bean.InvoiceEntryKey;
import com.rk.bean.ReturnEntryBean;
import com.rk.bean.ReturnEntryKey;
import com.rk.bean.UserBean;
import com.rk.model.CreditNote;
import com.rk.model.InvoiceEntry;
import com.rk.model.Party;
import com.rk.model.ReturnEntry;
import com.rk.service.CityService;
import com.rk.service.CreditNoteService;
import com.rk.service.InvoiceEntryService;
import com.rk.service.InvoiceService;
import com.rk.service.PartyService;
import com.rk.service.PaymentService;
import com.rk.service.ProductService;
import com.rk.service.ReturnEntryService;
import com.rk.service.StateService;

@Controller
public class CreditNoteController {
	@Autowired
	private CreditNoteService creditNoteService;
	@Autowired
	private ReturnEntryService returnEntryService;
	@Autowired
	private InvoiceEntryService invoiceEntryService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private PaymentService paymentService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new CreditNoteBean());
		model.addAttribute("commandEntry", new ReturnEntryBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("creditNote", null);
		model.addAttribute("returnEntry", null);
		model.addAttribute("creditNotes",  prepareListofBean(creditNoteService.listCreditNotes()));
		model.addAttribute("parties",  partyService.listParties());
	}
	
	/* Credit Note */
	@RequestMapping(value="/creditNote", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("creditNote");
	}

	@RequestMapping(value = "/creditNote/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  CreditNoteBean creditNoteBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		CreditNoteBean bean = prepareBean(creditNoteService.getCreditNote(creditNoteBean.getCreditNoteNo()));
		if(bean.getClear()) {
			return new ModelAndView("redirect:/creditNote");
		}
		try {
			creditNoteService.deleteCreditNote(prepareModel(creditNoteBean));
		}
		catch(Exception e) {
			return new ModelAndView("creditNote","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/creditNote");
	}
	
	@RequestMapping(value = "/creditNote/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  CreditNoteBean creditNoteBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		CreditNoteBean bean = prepareBean(creditNoteService.getCreditNote(creditNoteBean.getCreditNoteNo()));
		if(bean.getClear()) {
			return new ModelAndView("redirect:/creditNote");
		}
		model.put("creditNote", bean);
		model.put("action", "Edit");
		return new ModelAndView("creditNote", model);
	}
	
	@RequestMapping(value = "/creditNote/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") CreditNoteBean creditNoteBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("creditNote",model);
		}
		
		creditNoteBean.setPaymentID(null);
		CreditNote creditNote = prepareModel(creditNoteBean);
		creditNoteService.addCreditNote(creditNote);
		return new ModelAndView("redirect:/creditNote");
	}
	
	@RequestMapping(value = "/creditNote/print/{creditNoteNo}/{type}", method = RequestMethod.GET)
	public ModelAndView print(HttpSession session,
			@PathVariable("creditNoteNo") Integer creditNoteNo,
			@PathVariable("type") String type) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals("original") && !type.equals("duplicate")) {
			return new ModelAndView("redirect:/creditNote");
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		CreditNoteBean bean = prepareBean(creditNoteService.getCreditNote(creditNoteNo));
		model.put("creditNote", bean);
		Party party = partyService.getParty(bean.getPartyID());
		model.put("party", party);
		model.put("state", stateService.getState(
				cityService.getCity(party.getCity()).getState()
				));
		model.put("contact", partyService.getContact(party));
		model.put("email", partyService.getEmail(party));
		model.put("entries", prepareListofEntryBean(returnEntryService.listReturnEntries(creditNoteNo)));
		model.put("type", type);
		ClientBean client = (ClientBean)session.getAttribute("client");
		model.put("firmName",client.getFirmName().toUpperCase());
		return new ModelAndView("creditNotePrint", model);
	}
	
	/* Return Entry */
	@RequestMapping(value = "/creditNote/addreturns/{creditNoteNo}", method = RequestMethod.GET)
	public ModelAndView listReturnEntries(@PathVariable("creditNoteNo") Integer creditNoteNo, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		CreditNote creditNote = creditNoteService.getCreditNote(creditNoteNo);
		if(creditNote == null) {
			return new ModelAndView("redirect:/creditNote");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		CreditNoteBean creditNoteBean = prepareBean(creditNote);
		model.put("creditNote", creditNoteBean);
		model.put("entries", prepareListofEntryBean(returnEntryService.listReturnEntries(creditNoteNo)));
		model.put("invoices", invoiceService.listInvoices(creditNoteBean.getPartyID()));
		model.put("products", productService.listProducts());
		return new ModelAndView("addreturns",model);
	}
	
	@RequestMapping(value = "/creditNote/addreturns/{creditNoteNo}/add", method = RequestMethod.POST)
	public ModelAndView saveInvoiceEntry(HttpSession session,
			@PathVariable("creditNoteNo") Integer creditNoteNo,
			@Valid @ModelAttribute("commandEntry") ReturnEntryBean returnEntryBean, 
			BindingResult result) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		
		ReturnEntry returnEntry = prepareEntryModel(returnEntryBean);
		InvoiceEntry invoiceEntry = invoiceEntryService.getInvoiceEntry(
				new InvoiceEntryKey(returnEntryBean.getInvoiceID(), returnEntryBean.getProductID()));
		
//		(If goods from same entry are returned earlier in another Credit Note.)
		Integer initQuantity = returnEntryService.getQuantity(returnEntryBean.getKey()); 
		if(invoiceEntry == null || 
				invoiceEntry.getQuantity() < initQuantity+returnEntryBean.getQuantity()) {
			Map<String,Object> model = new HashMap<String,Object>();
			CreditNote creditNote = creditNoteService.getCreditNote(returnEntry.getCreditNoteNo());
			CreditNoteBean creditNoteBean = prepareBean(creditNote);
			model.put("creditNote", creditNoteBean);
			model.put("entries", prepareListofEntryBean(returnEntryService.listReturnEntries(creditNoteNo)));
			model.put("invoices", invoiceService.listInvoices(creditNoteBean.getPartyID()));
			model.put("products", productService.listProducts());
			model.put("errorMessage", "Please entry valid details.");
			return new ModelAndView("addreturns",model);
		}
		
		returnEntryService.addReturnEntry(returnEntry);
		return new ModelAndView("redirect:/creditNote/addreturns/" + creditNoteNo);
	}
	
	@RequestMapping(value = "/creditNote/addreturns/{creditNoteNo}/edit", method = RequestMethod.GET)
	public ModelAndView editInvoiceEntry(HttpSession session,
			@PathVariable("creditNoteNo") Integer creditNoteNo,
			@RequestParam("invoiceID") Integer invoiceID,
			@RequestParam("productID") String productID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		ReturnEntryBean returnEntryBean = prepareEntryBean(returnEntryService.getReturnEntry(new ReturnEntryKey(creditNoteNo,invoiceID,productID)));
		CreditNoteBean creditNoteBean = prepareBean(creditNoteService.getCreditNote(creditNoteNo));
		model.put("creditNote", creditNoteBean);
		model.put("entries", prepareListofEntryBean(returnEntryService.listReturnEntries(creditNoteNo)));
		model.put("invoices", invoiceService.listInvoices(creditNoteBean.getPartyID()));
		model.put("products", productService.listProducts());
		model.put("returnEntry", returnEntryBean);
		return new ModelAndView("addreturns",model);
	}
	
	@RequestMapping(value = "/creditNote/addreturns/{creditNoteNo}/delete", method = RequestMethod.GET)
	public ModelAndView deleteInvoiceEntry(HttpSession session,
			@PathVariable("creditNoteNo") Integer creditNoteNo,
			@RequestParam("invoiceID") Integer invoiceID,
			@RequestParam("productID") String productID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		returnEntryService.deleteReturnEntry(
				returnEntryService.getReturnEntry(new ReturnEntryKey(creditNoteNo,invoiceID,productID)));
		return new ModelAndView("redirect:/creditNote/addreturns/" + creditNoteNo);
	}
	
	/* Credit Note */
	public CreditNote prepareModel(CreditNoteBean creditNoteBean){
		CreditNote creditNote = new CreditNote();
		creditNote.setCreditNoteNo(creditNoteBean.getCreditNoteNo());
		creditNote.setDateOfReturn(creditNoteBean.getDateOfReturn());
		creditNote.setPartyID(creditNoteBean.getPartyID());
		creditNote.setPaymentID(creditNoteBean.getPaymentID());
		return creditNote;
	}
	
	public List<CreditNoteBean> prepareListofBean(List<CreditNote> creditNotes){
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
	
	/* Return Entry */
	private ReturnEntry prepareEntryModel(ReturnEntryBean returnEntryBean){
		ReturnEntry returnEntry = new ReturnEntry();
		returnEntry.setCreditNoteNo(returnEntryBean.getCreditNoteNo());
		returnEntry.setProductID(returnEntryBean.getProductID());
		returnEntry.setInvoiceID(returnEntryBean.getInvoiceID());
		returnEntry.setQuantity(returnEntryBean.getQuantity());
		return returnEntry;
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
