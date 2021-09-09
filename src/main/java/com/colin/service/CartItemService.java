package com.colin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colin.models.CartItem;
import com.colin.models.Product;
import com.colin.repo.CartItemRepository;

import lombok.val;

@Service
public class CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;
	
	public List<CartItem> getUserCart(Long userId) {
		List<CartItem> cartItems = new ArrayList<>();
		cartItemRepository.findAll().forEach(cartItems::add);
		List <CartItem> cartProducts = new ArrayList<>();
		cartItems.stream()
			.filter(c -> userId.equals(c.getUser().getId()))
			.forEach(cartProducts::add);
		/*val cartProducts = cartItems.stream()
				.filter(c -> userId.equals(c.getUser().getId()))
				.collect(Collectors.toList());*/
		
		return cartProducts;
	}
	
	public void createCartItem(CartItem c) {
		cartItemRepository.save(c);
	}
	
	public void deleteByUserId(Long userId) {
		List<CartItem> cartItems = new ArrayList<>();
		cartItemRepository.findAll().forEach(cartItems::add);
		cartItems.stream()
			.filter(c -> userId.equals(c.getUser().getId()))
			.forEach(c -> cartItemRepository.deleteById(c.getId()));
	}
	
	public void deleteByUserAndProductId(Long userId, Long productId) {
		List<CartItem> cartItems = new ArrayList<>();
		cartItemRepository.findAll().forEach(cartItems::add);
		for (CartItem c : cartItems) {
			if (c.getProduct().getId() == productId && c.getUser().getId() == userId) {
				cartItemRepository.deleteById(c.getId());
			}
		}
	}
}
