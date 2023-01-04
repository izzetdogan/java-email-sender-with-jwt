package com.example.security.repository;

import com.example.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User>  findByEmail(String email);
}
