package com.ecom.ecom.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ecom.ecom.models.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    Optional<Category> findByName(String name);
    
}
