package com.colin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colin.service.StripeService;
import com.stripe.model.Charge;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {

	private StripeService stripeService;

	@Autowired
	PaymentGatewayController(StripeService stripeService) {
		this.stripeService = stripeService;
	}

	@PostMapping("/charge")
	public Charge chargeCard(@RequestHeader(value = "token") String token,
			@RequestHeader(value = "amount") Double amount) throws Exception {
		return this.stripeService.chargeNewCard(token, amount);
	}
}
