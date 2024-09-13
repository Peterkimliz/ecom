package com.ecom.ecom.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.VariantType;

@Repository
public interface VariantTypesRepository extends MongoRepository<VariantType,String> {
    Optional<VariantType> findByType(String name);
    
}
