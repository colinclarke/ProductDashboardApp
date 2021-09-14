package com.colin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colin.models.CartItem;
import com.colin.models.Product;
import com.colin.service.CartItemService;
import com.colin.service.ProductService;
import com.colin.service.StripeService;
import com.stripe.model.Charge;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {

	private StripeService stripeService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductService productService;

	@Autowired
	PaymentGatewayController(StripeService stripeService) {
		this.stripeService = stripeService;
	}

	@PostMapping("/charge")
	public Charge chargeCard(@RequestHeader(value = "token") String token,
			@RequestHeader(value = "amount") Double amount, @RequestHeader(value = "userid") Long userid)
			throws Exception {
		Charge charge = this.stripeService.chargeNewCard(token, amount);
		List<CartItem> list = cartItemService.getUserCart(userid);
		for (CartItem item : list) {
			Product p = new Product();
			p = productService.getById(item.getProduct().getId()).orElse(null);
			if (p.getQuantity() - item.getQuantity() == 0) {
				productService.deleteProduct(p.getId());
			} else {
				p.setQuantity(p.getQuantity() - item.getQuantity());
				productService.updateProduct(p);
			}
		}
		cartItemService.deleteByUserId(userid);
		return charge;
	}
}
