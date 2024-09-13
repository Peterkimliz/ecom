package com.ecom.ecom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Variant;

@Repository
public interface VariantRepository extends MongoRepository<Variant,String>{

    Optional<Variant>findByName(String name);

    @Query("{'variantType.id': ?0}")
    List<Variant> findByVariantType(String id);
    
}
