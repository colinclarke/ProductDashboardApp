package com.colin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.repo.CategoryRepository;
import com.colin.service.ProductService;

@RestController()
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("")
	public List<Product> listAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("")
	public List<Product> searchProducts(@RequestParam String searchKey) {
		return productService.getSearchedProduct(searchKey);
	}

	@PostMapping("/new")
	public ResponseEntity<ProductCategory> addNewProduct(@Valid @RequestBody ProductCategory pc) {

		Category category = categoryRepository.findByName(pc.getCategory().getName()).orElse(pc.getCategory());
		pc.getProduct().setCategory(category);
		category.addProduct(pc.getProduct());
		categoryRepository.save(category);
		return new ResponseEntity<ProductCategory>(pc, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ProductCategory editProduct(@PathVariable long id, @Valid @RequestBody ProductCategory pc) {
		Product product = pc.getProduct();
		Category category = categoryRepository.findByName(pc.getCategory().getName()).orElse(pc.getCategory());
		Product p1 = productService.getById(id).orElse(null);
		p1.setName(product.getName());
		p1.setPrice(product.getPrice());
		p1.setQuantity(product.getQuantity());
		p1.setCategory(category);
		categoryRepository.save(category);
		productService.updateProduct(p1);
		return pc;

	}

	@GetMapping("/{id}")
	public ProductCategory getProductById(@PathVariable long id) {
		Product product = productService.getById(id).orElse(null);
		if (product == null) {
			return null;
		}
		return new ProductCategory(product, product.getCategory());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}

}
