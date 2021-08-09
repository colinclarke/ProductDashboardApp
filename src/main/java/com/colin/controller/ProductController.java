package com.colin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.repo.CategoryRepository;
import com.colin.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("")
	public ModelAndView listAllProducts(ModelAndView mav) {
		List<Category> c = new ArrayList<>();
		categoryRepository.findAll().forEach(c::add);
		mav.addObject("categories", c);
		mav.addObject("productList", productService.getAllProducts());
		mav.addObject("total", productService.getTotalPriceOfAllProducts());
		mav.addObject("search", false);
		mav.setViewName("products");
		return mav;
	}
	
	@PostMapping("")
	public ModelAndView searchProducts(@RequestParam String searchKey, ModelAndView mav) {
		List<Category> c = new ArrayList<>();
		categoryRepository.findAll().forEach(c::add);
		
		
		List<Product> sp = productService.getSearchedProduct(searchKey);
		mav.addObject("searched", sp);
		mav.addObject("total", productService.getSearchedProductTotal(searchKey));
		mav.addObject("search", true);
		mav.setViewName("products");
		
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView addNewProduct(ModelAndView mav) {
		mav.addObject("pc", new ProductCategory());
		mav.setViewName("product-form");
		return mav;
	}

	@PostMapping("/new")
	public String addNewProduct(@Valid @ModelAttribute("pc") ProductCategory pc, BindingResult bindingResult,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.put("product", new Product());
			return "product-form";
		}
		Category category = categoryRepository.findByName(pc.getCategory().getName()).orElse(pc.getCategory());
		pc.getProduct().setCategory(category);
		category.addProduct(pc.getProduct());
		categoryRepository.save(category);
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView editProduct(@PathVariable long id, ModelAndView mav) {
		Product product = productService.getById(id).orElse(null);
		if (product == null) {
			mav.addObject("alertMessage", "The product you tried to edit does not exist!");
			mav.addObject("productList", productService.getAllProducts());
			mav.setViewName("products");
			return mav;
		}
		mav.addObject("product", product);
		mav.addObject("pc", new ProductCategory(product, product.getCategory()));
		mav.setViewName("product-form");
		return mav;
	}

	@PostMapping("/edit/{id}")
	public String editProduct(@Valid @ModelAttribute("pc") ProductCategory pc, BindingResult bindingResult,
			ModelMap map) {
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		Product product = pc.getProduct();
		Category category = categoryRepository.findByName(pc.getCategory().getName()).orElse(pc.getCategory());
		product.setCategory(category);
		boolean priceChanged = productService.priceChanged(product);
		boolean nameChanged = productService.nameChanged(product);
		if (!productService.isAdmin() && (priceChanged || nameChanged)) {
			if (priceChanged) {
				bindingResult.rejectValue("product.price", "error.product", "Only admins can edit the price");
			}
			if (nameChanged) {
				bindingResult.rejectValue("product.name", "error.product", "Only admins can edit the name");
			}
			map.put("product", product);
			map.put("pc", pc);
			return "product-form";
		}
		categoryRepository.save(category);
		productService.updateProduct(product);
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
