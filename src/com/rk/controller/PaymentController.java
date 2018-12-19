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

import com.google.gson.Gson;
import com.rk.bean.CreditNoteBean;
import com.rk.bean.InvoiceBean;
import com.rk.bean.InvoiceEntryKey;
import com.rk.bean.PaymentBean;
import com.rk.bean.ReturnEntryBean;
import com.rk.bean.ReturnEntryKey;
import com.rk.bean.UserBean;
import com.rk.model.CreditNote;
import com.rk.model.Invoice;
import com.rk.model.InvoiceEntry;
import com.rk.model.Payment;
import com.rk.model.ReturnEntry;
import com.rk.service.CreditNoteService;
import com.rk.service.InvoiceEntryService;
import com.rk.service.InvoiceService;
import com.rk.service.PartyService;
import com.rk.service.PaymentService;
import com.rk.service.ReturnEntryService;
import com.rk.service.VehicleService;
import com.rk.service.ProductService;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private CreditNoteService creditNoteService;
	@Autowired
	private ReturnEntryService returnEntryService;
	@Autowired
	private InvoiceEntryService invoiceEntryService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PartyService partyService;

	public static final String INVOICE = "INVOICE";
	public static final String CREDITNOTE = "CREDITNOTE";
	public static final String MANUFACTURER = "MANUFACTURER";
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String TRANSPORT = "TRANSPORT";
	public static final String PARTYAGENT = "PARTYAGENT";
	
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new PaymentBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("payment", null);
	}

//	@RequestMapping(value = "/payment/delete", method = RequestMethod.GET)
//	public ModelAndView delete(@Valid @ModelAttribute("command")  PaymentBean paymentBean,
//			BindingResult result, HttpSession session) {
//		if(!UserBean.isAuthenticated(session)) {
//			return new ModelAndView("redirect:/login");
//		}
//		paymentService.deletePayment(prepareModel(paymentBean));
//		return new ModelAndView("redirect:/payment");
//	}
	
//	@RequestMapping(value = "/payment/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@Valid @ModelAttribute("command")  PaymentBean paymentBean,
//			BindingResult result, HttpSession session) {
//		if(!UserBean.isAuthenticated(session)) {
//			return new ModelAndView("redirect:/login");
//		}
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("payment", prepareBean(paymentService.getPayment(paymentBean.getPaymentID())));
//		model.put("action", "Edit");
//		return new ModelAndView("payment", model);
//	}
	
	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public ModelAndView allPay(HttpSession session) {
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("payments",  paymentService.listModePayments());
		return new ModelAndView("allpayments",model);
	}

	@RequestMapping(value="/payment/debit", method = RequestMethod.GET)
	public ModelAndView payDebit(HttpSession session,
			@RequestParam("type") String type,
			@RequestParam("id") Integer id) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals(MANUFACTURER) &&
				!type.equals(EMPLOYEE) &&
					!type.equals(TRANSPORT) &&
						!type.equals(PARTYAGENT)) {
			return new ModelAndView("redirect:/");
		}
		
		Map<String,Object> model = new HashMap<String,Object>();
		PaymentBean paymentBean = new PaymentBean();
		paymentBean.setType(type);
		model.put("command", paymentBean);
		model.put("type", type);
		model.put("id", id);
		return new ModelAndView("payment",model);
	}

	@RequestMapping(value = "/payment/debit", method = RequestMethod.POST)
	public ModelAndView saveDebit(@Valid @ModelAttribute("command") PaymentBean paymentBean, 
			BindingResult result, HttpSession session,
			@RequestParam("id") Integer id) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("payment",model);
		}
		
		String type = paymentBean.getType();
		String table = "", colName = "";
		if(type.equals(MANUFACTURER)) {
			table = "manuPay";
			colName = "manuID";
		}
		else if(type.equals(EMPLOYEE)) {
			table = "empPay";
			colName = "empID";
		}
		else if(type.equals(TRANSPORT)) {
			table = "vehiclePay";
			colName = "transportAgentID";
		}
		else if(type.equals(PARTYAGENT)) {
			table = "agentPay";
			colName = "partyAgentID";
		}
		else {
			return new ModelAndView("redirect:/");
		}
		
		Payment payment = prepareModel(paymentBean);
		paymentService.addPayment(payment);
		paymentService.linkPayment(table,colName,payment,id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/payment/summary/debit/{type}/{id}", method = RequestMethod.GET)
	public ModelAndView paySummary(HttpSession session,
			@PathVariable("type") String type,
			@PathVariable("id") Integer id) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals(MANUFACTURER) &&
				!type.equals(EMPLOYEE) &&
					!type.equals(TRANSPORT) &&
						!type.equals(PARTYAGENT)) {
			return new ModelAndView("redirect:/");
		}
		
		String table = "", colName = "";
		if(type.equals(MANUFACTURER)) {
			table = "manuPay";
			colName = "manuID";
		}
		else if(type.equals(EMPLOYEE)) {
			table = "empPay";
			colName = "empID";
		}
		else if(type.equals(TRANSPORT)) {
			table = "vehiclePay";
			colName = "transportAgentID";
		}
		else if(type.equals(PARTYAGENT)) {
			table = "agentPay";
			colName = "partyAgentID";
		}
			
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("payments",paymentService.listDebitPayments(table,colName,id));
		return new ModelAndView("allpayments",model);
	}
	
	@RequestMapping(value = "/payment/bills", method = RequestMethod.GET)
	public ModelAndView payBills(HttpSession session,
			@RequestParam("type") String type,
			@RequestParam("partyID") String partyID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals(INVOICE) &&
				!type.equals(CREDITNOTE)) {
			return new ModelAndView("redirect:/");
		}
		Map<String,Object> model = new HashMap<String,Object>();
		PaymentBean paymentBean = new PaymentBean();
		paymentBean.setType(type);
		model.put("command", paymentBean);
		model.put("type", type);
		
		if(type.equals(INVOICE)) {
			model.put("invoices", invoiceService.listUnpaidInvoices(partyID));
			model.put("creditNotes", null);
		}
		else if(type.equals(CREDITNOTE)) {
			model.put("invoices", null);
			model.put("creditNotes", creditNoteService.listUnpaidCreditNotes(partyID));
		}
		
		return new ModelAndView("paymentBills",model);
	}
	
	@RequestMapping(value = "/payment/bills", method = RequestMethod.POST)
	public ModelAndView saveBills(@Valid @ModelAttribute("command") PaymentBean paymentBean, 
			BindingResult result, HttpSession session,
			@RequestParam("ids") List<Integer> ids) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("paymentBills",model);
		}
		String type = paymentBean.getType();
		if(!type.equals(INVOICE) && !type.equals(CREDITNOTE)) {
			return new ModelAndView("redirect:/");
		}
		if(ids == null || ids.isEmpty()) {
			System.out.println("Empty");
			return new ModelAndView("redirect:/");
		}
		System.out.println(ids);
		
		Payment payment = prepareModel(paymentBean);
		paymentService.addPayment(payment);
		
		Integer amount = new Integer(0);
		if(type.equals(INVOICE)) {
			for(Integer id : ids) {
				Invoice invoice = invoiceService.getInvoice(id);
				invoice.setPaymentID(payment.getPaymentID());
				invoiceService.addInvoice(invoice);
				amount += prepareBean(invoice).getRoundedTotal();
			}
		}
		else if(type.equals(CREDITNOTE)) {
			for(Integer id : ids) {
				CreditNote creditNote = creditNoteService.getCreditNote(id);
				creditNote.setPaymentID(payment.getPaymentID());
				creditNoteService.addCreditNote(creditNote);
				amount += prepareBean(creditNote).getRoundedTotal();
			}
		}
		
		payment.setAmount(amount);		
		paymentService.addPayment(payment);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/payment/summary/bills/{type}/{id}", method = RequestMethod.GET)
	public ModelAndView payBillsSummary(HttpSession session,
			@PathVariable("type") String type,
			@PathVariable("id") Integer id) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(!type.equals(INVOICE) &&
				!type.equals(CREDITNOTE)) {
			return new ModelAndView("redirect:/");
		}
		Integer payID = null;
		if(type.equals(INVOICE)) {
			payID = invoiceService.getInvoice(id).getPaymentID();
		}
		else if(type.equals(CREDITNOTE)) {
			payID = creditNoteService.getCreditNote(id).getPaymentID();
		}
		else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("paymentSummary","payment",prepareBean(paymentService.getPayment(payID)));
	}
	
	private List<Map<Object,Object>> convert(List<Object[]> list) {
		List<Map<Object,Object>> maplist = new ArrayList<Map<Object,Object>>();
		Map<Object,Object> map = null;
		for(Object[] l : list) {
			map = new HashMap<Object,Object>();
			if(l[1] == null) {
				l[1] = 0;
			}
			map.put("label", l[0]);
			map.put("y", l[1]);
			maplist.add(map);
		}
		return maplist;
	}
	
	@RequestMapping(value = "/stats/profit", method = RequestMethod.GET)
	public ModelAndView profitStats(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		List<Object[]> profits = partyService.listPartyProfits();
		List<Map<Object,Object>> profitList = convert(profits);
		Gson gsonObj = new Gson();
		String dataProfit = gsonObj.toJson(profitList);
		return new ModelAndView("profitStats","profits",dataProfit);
	}
	
	private Payment prepareModel(PaymentBean paymentBean){
		Payment payment = new Payment();
		payment.setPaymentID(paymentBean.getPaymentID());
		payment.setAmount(paymentBean.getAmount());
		payment.setDateOfPay(paymentBean.getDateOfPay());
		payment.setModeOfPay(paymentBean.getModeOfPay());
		payment.setType(paymentBean.getType());
		return payment;
	}
	
//	private List<PaymentBean> prepareListofBean(List<Payment> payments){
//		List<PaymentBean> beans = null;
//		if(payments != null && !payments.isEmpty()){
//			beans = new ArrayList<PaymentBean>();
//			for(Payment payment : payments){
//				beans.add(prepareBean(payment));
//			}
//		}
//		return beans;
//	}
	
	private PaymentBean prepareBean(Payment payment){
		PaymentBean bean = new PaymentBean();
		bean.setPaymentID(payment.getPaymentID());
		bean.setAmount(payment.getAmount());
		bean.setDateOfPay(payment.getDateOfPay());
		bean.setModeOfPay(payment.getModeOfPay());
		bean.setType(payment.getType());
		return bean;
	}
	
	private InvoiceBean prepareBean(Invoice invoice){
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
	
	private CreditNoteBean prepareBean(CreditNote creditNote){
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
