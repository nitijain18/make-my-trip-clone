package com.makemytrip.makemytrip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.makemytrip.makemytrip.model.User;


public interface UserRepository extends MongoRepository<User,String>{
  User findByEmail(String email);
}
