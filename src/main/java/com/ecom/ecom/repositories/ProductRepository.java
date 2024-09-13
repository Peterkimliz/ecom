package com.ecom.ecom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
