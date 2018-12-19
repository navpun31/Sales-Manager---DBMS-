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

import com.rk.bean.CategoryBean;
import com.rk.bean.UserBean;
import com.rk.model.Category;
import com.rk.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new CategoryBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("category", null);
		model.addAttribute("categories",  prepareListofBean(categoryService.listCategories()));
	}
	
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("category");
	}

	@RequestMapping(value = "/category/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  CategoryBean categoryBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			categoryService.deleteCategory(prepareModel(categoryBean));
		}
		catch(Exception e) {
			return new ModelAndView("category","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/category");
	}
	
	@RequestMapping(value = "/category/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  CategoryBean categoryBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("category", prepareBean(categoryService.getCategory(categoryBean.getHsn())));
		model.put("action", "Edit");
		return new ModelAndView("category", model);
	}
	
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") CategoryBean categoryBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("category",model);
		}
		
		Category category = prepareModel(categoryBean);
		categoryService.addCategory(category);
		return new ModelAndView("redirect:/category");
	}
	
	private Category prepareModel(CategoryBean categoryBean){
		Category category = new Category();
		category.setHsn(categoryBean.getHsn());
		category.setName(categoryBean.getName());
		category.setDescription(categoryBean.getDescription());
		return category;
	}
	
	private List<CategoryBean> prepareListofBean(List<Category> categories){
		List<CategoryBean> beans = null;
		if(categories != null && !categories.isEmpty()){
			beans = new ArrayList<CategoryBean>();
			for(Category category : categories){
				beans.add(prepareBean(category));
			}
		}
		return beans;
	}
	
	private CategoryBean prepareBean(Category category){
		CategoryBean bean = new CategoryBean();
		bean.setHsn(category.getHsn());
		bean.setName(category.getName());
		bean.setDescription(category.getDescription());		
		return bean;
	}
}
