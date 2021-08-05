package com.colin.controller;

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

import com.colin.models.Product;
import com.colin.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ModelAndView listAllProducts(ModelAndView mav) {
		mav.addObject("productList", productService.getAllProducts());
		mav.addObject("total", productService.getTotalPriceOfAllProducts());
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
	public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.put("product", new Product());
			return "product-form";
		}
		productService.createProduct(product);
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
		mav.setViewName("product-form");
		return mav;
	}

	@PostMapping("/edit/{id}")
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		boolean priceChanged = productService.priceChanged(product);
		boolean nameChanged = productService.nameChanged(product);
		if (!productService.isAdmin() && (priceChanged || nameChanged)) {
			if (priceChanged) {
				bindingResult.rejectValue("price", "error.product", "Only admins can edit the price");
			}
			if (nameChanged) {
				bindingResult.rejectValue("name", "error.product", "Only admins can edit the name");
			}
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
