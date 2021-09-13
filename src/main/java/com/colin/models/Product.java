package com.colin.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 1)
	private long id;
	@NotBlank(message = "Product name is mandatory")
	private String name;
	@Positive(message = "Product quantity must be a positive integer")
	private int quantity;
	@Positive(message = "Product price must be greater than zero")
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	@JsonBackReference(value = "category")
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "cart")
	List<CartItem> cartItems = new ArrayList<CartItem>();

	@Override
	public String toString() {
		return "Id: " + id + " Name: " + name + " Quantity: " + quantity + " Price: " + price + " Category: "
				+ category;
	}
}