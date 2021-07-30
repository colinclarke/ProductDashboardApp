package com.colin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colin.models.Product;
import com.colin.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("")
	public ModelAndView listAllProducts(ModelAndView mav) {
		System.out.println(userDetailsService.loadUserByUsername("colin").getAuthorities());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
		mav.addObject("productList", productService.getAllProducts());
		mav.setViewName("products");
		return mav;
	}

	@GetMapping("/new")
	public ModelAndView addNewProduct(ModelAndView mav) {
		mav.addObject("product", new Product());
		mav.setViewName("product-form");
		return mav;
	}

	@PostMapping("/new")
	public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		productService.createProduct(product);
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView editProduct(@PathVariable long id, ModelAndView mav) {
		mav.addObject("product", productService.getById(id).orElse(new Product()));
		mav.setViewName("product-form");
		return mav;
	}

	@PostMapping("/edit/{id}")
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		productService.updateProduct(product);
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
