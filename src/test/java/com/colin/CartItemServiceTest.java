package com.colin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.CartItem;
import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.CartItemRepository;
import com.colin.repo.ProductRepository;
import com.colin.service.CartItemService;
import com.colin.service.ProductService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemServiceTest {

	@MockBean
	CartItemRepository cartItemRepository;
	
	@Autowired
	CartItemService cartItemService;
	
	@Test
	public void testGetUserCart() {
		ArrayList<CartItem> cartItems = new ArrayList<>();
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		cartItems.add(cI);
		
		when(cartItemRepository.findAll()).thenReturn(cartItems);
		
		List<CartItem> userCart = cartItemService.getUserCart(1l);
		
		
		assertEquals(userCart.size(), 1);
		assertTrue(userCart.get(0).getProduct().getName().equals("Milk"));
		assertTrue(userCart.get(0).getProduct().getQuantity() == 3);
		assertTrue(userCart.get(0).getProduct().getPrice() == 2.99);
		assertTrue(userCart.get(0).getProduct().getCategory().getName().equals("Dairy"));
		assertTrue(userCart.get(0).getUser().getUsername().equals("Percy"));
		assertTrue(userCart.get(0).getQuantity() == 2);
		
	}
	
	@Test
	public void testCreateCartItem() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		
		when(cartItemRepository.save(cI)).thenReturn(cI);
		
		cartItemService.createCartItem(cI);
		assertNotNull(cI);
	}
	
	@Test
	public void testDeleteCartItemByUserId() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		
		when(cartItemRepository.save(cI)).thenReturn(cI);
		
		cartItemService.createCartItem(cI);
		

		
		cartItemService.deleteByUserId(1l);
		
		assertNull(cartItemRepository.findById(1l).orElse(null));
		

	}
	
	@Test
	public void testDeleteCartItemByUserAndProductId() {
		Category c = new Category(1, "Dairy", new ArrayList<>());
		Product p = new Product(1, "Milk", 3, 2.99, c, new ArrayList<>());
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());
		CartItem cI = new CartItem(1, 2, LocalDateTime.now(), u, p);
		
		when(cartItemRepository.save(cI)).thenReturn(cI);
		
		cartItemService.createCartItem(cI);
		

		
		cartItemService.deleteByUserAndProductId(1l, 1l);
		
		assertNull(cartItemRepository.findById(1l).orElse(null));
		

	}
}
