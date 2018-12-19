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

import com.rk.bean.ColorBean;
import com.rk.bean.UserBean;
import com.rk.model.Color;
import com.rk.service.ColorService;
import com.rk.service.ProductService;

@Controller
public class ColorController {
	@Autowired
	private ColorService colorService;
	@Autowired
	private ProductService productService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new ColorBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("color", null);
		model.addAttribute("colors",  prepareListofBean(colorService.listColors()));
	}
	
	@RequestMapping(value="/color", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("color");
	}

	@RequestMapping(value = "/color/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  ColorBean colorBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			colorService.deleteColor(prepareModel(colorBean));
		}
		catch(Exception e) {
			return new ModelAndView("color","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/color");
	}
	
	@RequestMapping(value = "/color/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  ColorBean colorBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("color", prepareBean(colorService.getColor(colorBean.getColorCode())));
		model.put("action", "Edit");
		return new ModelAndView("color", model);
	}
	
	@RequestMapping(value = "/color/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") ColorBean colorBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("color",model);
		}
		
		Color color = prepareModel(colorBean);
		colorService.addColor(color);
		return new ModelAndView("redirect:/color");
	}
	
	@RequestMapping(value = "/color/products/{colorCode}", method = RequestMethod.GET)
	public ModelAndView products(HttpSession session,
			@PathVariable("colorCode") String colorCode) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("productlistColor", "products", productService.listColorProducts(colorCode));
	}
	
	private Color prepareModel(ColorBean colorBean){
		Color color = new Color();
		color.setColorCode(colorBean.getColorCode());
		color.setRed(colorBean.getRed());
		color.setGreen(colorBean.getGreen());
		color.setBlue(colorBean.getBlue());
		color.setColorName(colorBean.getColorName());
		return color;
	}
	
	private List<ColorBean> prepareListofBean(List<Color> colors){
		List<ColorBean> beans = null;
		if(colors != null && !colors.isEmpty()){
			beans = new ArrayList<ColorBean>();
			for(Color color : colors){
				beans.add(prepareBean(color));
			}
		}
		return beans;
	}
	
	private ColorBean prepareBean(Color color){
		ColorBean bean = new ColorBean();
		bean.setColorCode(color.getColorCode());
		bean.setRed(color.getRed());
		bean.setGreen(color.getGreen());
		bean.setBlue(color.getBlue());
		bean.setColorName(color.getColorName());
		return bean;
	}
}
