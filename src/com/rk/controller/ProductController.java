package com.rk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.rk.bean.*;
import com.rk.model.*;
import com.rk.service.*;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private StockService stockService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("action", "Add");
		model.addAttribute("command", new ProductBean());
		model.addAttribute("errorMessage", null);
		model.addAttribute("product", null);
		model.addAttribute("products",  prepareListofBean(productService.listProducts()));
		model.addAttribute("categories", categoryService.listCategories());
		model.addAttribute("colors", colorService.listColors());		
		model.addAttribute("image", new ImageBean());
	}
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("product");
	}

	@RequestMapping(value = "/product/delete", method = RequestMethod.GET)
	public ModelAndView delete(@Valid @ModelAttribute("command")  ProductBean productBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		try {
			productService.deleteProduct(prepareModel(productBean));
		}
		catch(Exception e) {
			return new ModelAndView("product","errorMessage","Cannot delete the entry.");
		}
		return new ModelAndView("redirect:/product");
	}
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET)
	public ModelAndView edit(@Valid @ModelAttribute("command")  ProductBean productBean,
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("product", prepareBean(productService.getProduct(productBean.getProductID())));
		model.put("action", "Edit");
		return new ModelAndView("product", model);
	}
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") ProductBean productBean, 
			BindingResult result, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMessage", "Please enter valid details.");
			return new ModelAndView("product",model);
		}
		
		Product product = prepareModel(productBean);
		productService.addProduct(product);
		if(productBean.getSize() != null) {
			productService.addSize(product, productBean.getSize());
		}
		else {
			productService.addSize(product, new ArrayList<String>());
		}

		if(productBean.getColor() != null) {
			productService.addColor(product, productBean.getColor());
		}
		else {
			productService.addColor(product, new ArrayList<String>());
		}
		return new ModelAndView("redirect:/product");
	}
	
	@RequestMapping(value = "/category/products/{hsn}", method = RequestMethod.GET)
	public ModelAndView products(HttpSession session,
			@PathVariable("hsn") Integer hsn) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("productlistCategory", "products", prepareListofBean(productService.listCategoryProducts(hsn)));
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
	
	@RequestMapping(value = "/stats/product", method = RequestMethod.GET)
	public ModelAndView stats(HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Object[]> sold = productService.listQuantitySold();
		List<Object[]> ret = productService.listQuantityReturned();
		List<Object[]> stock = stockService.listStock();
		
		List<Map<Object,Object>> soldList = convert(sold);
		List<Map<Object,Object>> retList = convert(ret);
		List<Map<Object,Object>> stockList = convert(stock);

		Gson gsonObj = new Gson();
		String dataSold = gsonObj.toJson(soldList);
		String dataRet = gsonObj.toJson(retList);
		String dataStock = gsonObj.toJson(stockList);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("sold", dataSold);
		model.put("ret", dataRet);
		model.put("stock", dataStock);
		return new ModelAndView("productStats",model);
	}
	
	/* Image */
	@RequestMapping(value = "/product/{productID}/image/save", method = RequestMethod.POST)
	public ModelAndView saveImage(
			@Valid @ModelAttribute("image") ImageBean imageBean, BindingResult result,
			@RequestParam("file") MultipartFile file,
			@PathVariable("productID") String productID,
			HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return new ModelAndView("redirect:/login");
		}
		if(result.hasErrors()) {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("errorMessage", "No image selected.");
			return new ModelAndView("image",model);
		}
		
		try {
			Blob blob = Hibernate.createBlob(file.getInputStream());
			
			imageBean.setProductID(productID);
			imageBean.setFilename(file.getOriginalFilename());
			imageBean.setContent(blob);
			imageBean.setContentType(file.getContentType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			imageService.save(prepareImageModel(imageBean));
			System.out.println("Saved");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/product");
	}

	@RequestMapping("/product/image/download/{imageId}")
	public String downloadImage(@PathVariable("imageId")	Integer imageId, 
			HttpServletResponse response, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return "redirect:/login";
		}
		
		Image image = imageService.get(imageId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" +image.getFilename()+ "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(image.getContentType());
			IOUtils.copy(image.getContent().getBinaryStream(), out);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@RequestMapping("/product/image/remove/{imageId}")
	public String removeImage(@PathVariable("imageId")
			Integer imageId, HttpSession session) {
		if(!UserBean.isAuthenticated(session)) {
			return "redirect:/login";
		}
		
		imageService.remove(imageId);
		return "redirect:/product";
	}
	
	private Image prepareImageModel(ImageBean imageBean){
		Image image = new Image();
		image.setContent(imageBean.getContent());
		image.setContentType(imageBean.getContentType());
		image.setFilename(imageBean.getFilename());
		image.setImageID(imageBean.getImageID());
		image.setProductID(imageBean.getProductID());
		return image;
	}
	
	private List<ImageBean> prepareListofImageBean(List<Image> images){
		List<ImageBean> beans = null;
		if(images != null && !images.isEmpty()){
			beans = new ArrayList<ImageBean>();
			for(Image image : images){
				beans.add(prepareImageBean(image));
			}
		}
		return beans;
	}
	
	private ImageBean prepareImageBean(Image image){
		ImageBean bean = new ImageBean();
		bean.setContent(image.getContent());
		bean.setContentType(image.getContentType());
		bean.setFilename(image.getFilename());
		bean.setImageID(image.getImageID());
		bean.setProductID(image.getProductID());
		return bean;
	}
	
	/* Product */
	private Product prepareModel(ProductBean productBean){
		Product product = new Product();
		product.setProductID(productBean.getProductID());
		product.setName(productBean.getName());
		product.setDescription(productBean.getDescription());
		product.setCost(productBean.getCost());
		product.setCategoryHSN(productBean.getCategoryHSN());
		return product;
	}
	
	private List<ProductBean> prepareListofBean(List<Product> products){
		List<ProductBean> beans = null;
		if(products != null && !products.isEmpty()){
			beans = new ArrayList<ProductBean>();
			for(Product product : products){
				beans.add(prepareBean(product));
			}
		}
		return beans;
	}
	
	private ProductBean prepareBean(Product product){
		ProductBean bean = new ProductBean();
		bean.setProductID(product.getProductID());
		bean.setName(product.getName());
		bean.setDescription(product.getDescription());
		bean.setCost(product.getCost());
		bean.setCategoryHSN(product.getCategoryHSN());
		bean.setSize(productService.getSize(product));
		bean.setColor(productService.getColor(product));
		if(product.getCategoryHSN() != null) {
			bean.setCategory(categoryService.getCategory(product.getCategoryHSN()));
		}
		else {
			bean.setCategory(null);
		}
		bean.setImage(prepareListofImageBean(imageService.list(product.getProductID())));
		return bean;
	}
}
