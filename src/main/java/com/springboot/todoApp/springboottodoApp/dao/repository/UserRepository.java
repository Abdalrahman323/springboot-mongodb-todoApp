package com.springboot.todoApp.springboottodoApp.dao.repository;

import com.springboot.todoApp.springboottodoApp.security.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends MongoRepository<AppUser,String> {

   AppUser findByEmail(String username);
}
