package com.colin.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

	//@JsonBackReference(value = "product")
	private Product product;
	@JsonBackReference(value = "category")
	private Category category;

}
