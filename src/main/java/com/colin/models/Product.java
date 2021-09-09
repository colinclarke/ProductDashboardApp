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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	List<CartItem> cartItems = new ArrayList<CartItem>(); 
	
	public Product(String name, int quantity, double price, Category category, List<CartItem> cartItems) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
		this.cartItems = cartItems;
	}
	
	@Override
	public String toString() {
		return "Id: " + id + " Name: " + name + " Quantity: " + quantity + " Price: " + price + " Category: " + category;
	}
}