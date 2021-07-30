package com.colin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

}
