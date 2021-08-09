package com.colin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.colin.models.Product;
import com.colin.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().forEach(list::add);
		return list;
	}

	public double getTotalPriceOfAllProducts() {
		double total = 0;
		for (Product p : productRepository.findAll()) {
			total += p.getPrice() * p.getQuantity();
		}
		return total;
	}
	
	
	public List<Product> getSearchedProduct(String category) {
		List<Product> products = new ArrayList<>();
		List<Product> productSearch = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		products.stream()
		.filter(p -> p.getCategory().getName().equalsIgnoreCase(category))
		.forEach(productSearch::add);
		return productSearch;
	}
	
	public double getSearchedProductTotal(String category) {
		double total = 0;
		List<Product> products = new ArrayList<>();
		List<Product> productSearch = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		products.stream()
		.filter(p -> p.getCategory().getName().equalsIgnoreCase(category))
		.forEach(productSearch::add);
		for (Product pr : productSearch) {
			total += pr.getPrice() * pr.getQuantity();
		}
		return total;
	}

	public void createProduct(Product product) {
		productRepository.save(product);
	}

	public Optional<Product> getById(long id) {
		return productRepository.findById(id);
	}

	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(long id) {
		productRepository.delete(getById(id).orElse(null));
	}

	public boolean isAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
	}

	public boolean priceChanged(Product product) {
		double oldPrice = getById(product.getId()).orElse(null).getPrice();
		return oldPrice != product.getPrice();
	}

	public boolean nameChanged(Product product) {
		String oldName = getById(product.getId()).orElse(null).getName();
		return !oldName.equals(product.getName());
	}

}
