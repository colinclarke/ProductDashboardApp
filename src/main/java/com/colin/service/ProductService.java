package com.colin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<ProductCategory> getAllProducts() {
		List<Product> list = new ArrayList<>();
		productRepository.findAll().forEach(list::add);
		List<ProductCategory> pcList = new ArrayList<>();
		for (Product p : list) {
			ProductCategory pc = new ProductCategory();
			pc.setCategory(p.getCategory());
			pc.setProduct(p);
			pcList.add(pc);
		}
		return pcList;
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
