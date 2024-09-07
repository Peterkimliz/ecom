package com.ecom.ecom.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecom.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel ,String> {

    Optional<UserModel>findByEmail(String email);

}
