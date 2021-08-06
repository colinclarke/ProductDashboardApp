package com.colin.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.colin.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	@Query("select c from Category c where c.name = ?1")
	Optional<Category> findByName(String name);

}
