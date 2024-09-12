package com.ecom.ecom.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.Poster;
@Repository
public interface PosterRepository extends MongoRepository<Poster, String>{
    
}
