package com.rk.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rk.dto.Product;

/**
 * @author RK
 *
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	@Query("{'Category.name':?0}")
	List<Product> findByCategory(String category);
	
}
