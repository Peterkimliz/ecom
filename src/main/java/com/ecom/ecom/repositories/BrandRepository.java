package com.ecom.ecom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Brand;
import java.util.Optional;
import java.util.List;



@Repository
public interface BrandRepository  extends MongoRepository<Brand,String>{

    Optional<Brand> findByName(String name);

   @Query("{'subcategory.id': ?0}")
    List<Brand> findBySubcategory(String id);
    
}
