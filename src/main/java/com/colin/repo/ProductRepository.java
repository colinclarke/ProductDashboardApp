package com.colin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.colin.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
