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

import com.rk.bean.StockBean;
import com.rk.bean.UserBean;
import com.rk.model.Stock;
import com.rk.service.ManufacturerService;
import com.rk.service.ProductService;
import com.rk.service.StockService;

@Controller
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private ManufacturerService manuService;
	@Autowired
	private ProductService productService;

//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }
	
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new StockBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("stock", null);
		model.addAttribute("stocks",  prepareListofBean(stockService.listStocks()));
		model.addAttribute("manufacturers",  manuService.listManufacturers());
		model.addAttribute("products",  productService.listProducts());
	}
	
	@RequestMapping(value="/stock", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("stock");
	}

	@RequestMapping(value = "/stock/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  StockBean stockBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			stockService.deleteStock(prepareModel(stockBean));
		}
		catch(Exception e) {
			return new ModelAndView("stock","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/stock");
	}
	
	@RequestMapping(value = "/stock/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  StockBean stockBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("stock", prepareBean(stockService.getStock(stockBean.getStockID())));
		model.put("action", "Edit");
		return new ModelAndView("stock", model);
	}
	
	@RequestMapping(value = "/stock/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") StockBean stockBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("stock",model);
		}
		
		Stock stock = prepareModel(stockBean);
		stockService.addStock(stock);
		return new ModelAndView("redirect:/stock");
	}
	
	@RequestMapping(value = "/stock/product/{productID}", method = RequestMethod.GET)
	public ModelAndView stock(HttpSession session,
			@PathVariable("productID") String productID) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		List<Stock> stocks = stockService.getProductStock(productID);
		return new ModelAndView("productStockSummary","stocks",prepareListofBean(stocks));
	}
	
	private Stock prepareModel(StockBean stockBean){
		Stock stock = new Stock();
		stock.setStockID(stockBean.getStockID());
		stock.setQuantity(stockBean.getQuantity());
		stock.setDateOfManu(stockBean.getDateOfManu());
		stock.setManuID(stockBean.getManuID());
		stock.setProductID(stockBean.getProductID());
		return stock;
	}
	
	private List<StockBean> prepareListofBean(List<Stock> stocks){
		List<StockBean> beans = null;
		if(stocks != null && !stocks.isEmpty()){
			beans = new ArrayList<StockBean>();
			for(Stock stock : stocks){
				beans.add(prepareBean(stock));
			}
		}
		return beans;
	}
	
	private StockBean prepareBean(Stock stock){
		StockBean bean = new StockBean();
		bean.setStockID(stock.getStockID());
		bean.setQuantity(stock.getQuantity());
		bean.setDateOfManu(stock.getDateOfManu());
		bean.setManuID(stock.getManuID());
		bean.setProductID(stock.getProductID());
		
		if(stock.getManuID() != null) {
			bean.setManufacturer(manuService.getManufacturer(stock.getManuID()));
		}
		else {
			bean.setManufacturer(null);
		}
		
		if(stock.getProductID() != null) {
			bean.setProduct(productService.getProduct(stock.getProductID()));
		}
		else {
			bean.setProduct(null);
		}
		
		return bean;
	}
}
