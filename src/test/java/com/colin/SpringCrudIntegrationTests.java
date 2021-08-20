package com.colin;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.repo.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class SpringCrudIntegrationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	ProductRepository productRepository;

	@Test
	void givenProduct_whenGetAllProducts_thenStatus200() {

		Category category = new Category(1, "Dairy", new ArrayList<>());
		Product product = new Product(1, "Milk", 3, 2.99, category);
		category.addProduct(product);
		productRepository.save(product);

		// mvc.perform(get("/products"))

	}

}
