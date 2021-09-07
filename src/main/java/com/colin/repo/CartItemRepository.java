package com.colin.repo;

import org.springframework.data.repository.CrudRepository;

import com.colin.models.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
