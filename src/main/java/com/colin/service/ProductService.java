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
		products.stream().filter(p -> p.getCategory().getName().equalsIgnoreCase(category)).forEach(productSearch::add);
		return productSearch;
	}

	public double getSearchedProductTotal(String category) {
		double total = 0;
		List<Product> products = new ArrayList<>();
		List<Product> productSearch = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		products.stream().filter(p -> p.getCategory().getName().equalsIgnoreCase(category)).forEach(productSearch::add);
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

	boolean priceChanged(Product product) {
		double oldPrice = getById(product.getId()).orElse(null).getPrice();
		return oldPrice != product.getPrice();
	}

	boolean nameChanged(Product product) {
		String oldName = getById(product.getId()).orElse(null).getName();
		return !oldName.equals(product.getName());
	}

	boolean categoryChanged(Product product, Category category) {
		String oldCategory = getById(product.getId()).orElse(null).getCategory().getName();
		return !oldCategory.equals(category.getName());
	}

	// If product is invalid bindings are set and returns true, otherwise false
	public boolean setBindingsIfInvalid(ProductCategory pc, BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
			pc.setProduct(new Product());
			map.put("product", pc.getProduct());
			map.put("pc", pc);
			return true;
		}
		Product product = pc.getProduct();
		if (!isAdmin()) {
			boolean priceChanged = priceChanged(product);
			boolean nameChanged = nameChanged(product);
			boolean categoryChanged = categoryChanged(product, pc.getCategory());
			if (priceChanged || nameChanged || categoryChanged) {
				if (priceChanged) {
					bindingResult.rejectValue("product.price", "error.product", "Only admins can edit the price");
				}
				if (nameChanged) {
					bindingResult.rejectValue("product.name", "error.product", "Only admins can edit the name");
				}
				if (categoryChanged) {
					bindingResult.rejectValue("category.name", "error.category", "Only admins can edit the category");
				}
				map.put("product", product);
				map.put("pc", pc);
				return true;
			}
		}
		if (product.getPrice() > 0 && product.getQuantity() > 0) {
			return false;
		} else {
			if (product.getPrice() <= 0) {
				bindingResult.rejectValue("product.price", "error.product", "Price must be greater than 0");
			}
			if (product.getQuantity() <= 0) {
				bindingResult.rejectValue("product.quantity", "error.product", "Quantity must be greater than 0");
			}
			map.put("product", product);
			map.put("pc", pc);
			return true;
		}
	}

}
