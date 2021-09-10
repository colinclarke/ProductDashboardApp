package com.colin.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {

	@Id
	@Column(name = "CART_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_generator")
	@SequenceGenerator(name = "cart_item_generator", sequenceName = "cart_item_seq", allocationSize = 1)
	private long id;
	private int quantity;
	private LocalDateTime ldt;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false)
	@JsonBackReference
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	@JsonBackReference
	private Product product;
}
