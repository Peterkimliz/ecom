 package com.ecom.ecom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Subcategory;
@Repository
public interface SubcategoryRepository  extends MongoRepository<Subcategory,String>{

    Optional<Subcategory> findByName(String name);

   @Query("{'categoryId.id': ?0}")
    List<Subcategory> findByCategoryId(String id);

    
} 