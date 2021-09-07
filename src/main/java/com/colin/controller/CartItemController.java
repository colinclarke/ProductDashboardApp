package com.colin.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colin.models.CartItem;
import com.colin.models.ProductQuantity;
import com.colin.service.CartItemService;
import com.colin.service.ProductService;
import com.colin.service.UserDetailsServiceImpl;

@RestController()
@RequestMapping("/api/cart")
public class CartItemController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	UserDetailsServiceImpl userDetails;

	@Autowired
	ProductService productService;

	@GetMapping("/{userid}")
	public List<CartItem> getAllProductsInCart(@PathVariable Long userid) {
		return cartItemService.getUserCart(userid);
	}

	@PostMapping("/{userid}")
	public ResponseEntity<ProductQuantity> addProductToCart(@PathVariable Long userid,
			@RequestBody ProductQuantity pq) {
		CartItem c = new CartItem();
		c.setQuantity(pq.getQuantity());
		c.setUser(userDetails.findById(userid));
		c.setProduct(productService.getById(pq.getProductId()).orElse(null));
		c.setLdt(LocalDateTime.now());
		cartItemService.createCartItem(c);
		return new ResponseEntity<ProductQuantity>(pq, HttpStatus.OK);
	}

	@DeleteMapping("/{userid}")
	public ResponseEntity<HttpStatus> deleteAllCartItems(@PathVariable Long userid) {
		cartItemService.deleteByUserId(userid);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{userid}/product/{productid}")
	public ResponseEntity<HttpStatus> deleteProductFromCart(@PathVariable Long userid, @PathVariable Long productid) {
		cartItemService.deleteByUserAndProductId(userid, productid);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}

}
