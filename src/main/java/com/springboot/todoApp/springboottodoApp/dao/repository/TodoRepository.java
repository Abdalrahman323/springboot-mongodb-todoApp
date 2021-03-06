package com.springboot.todoApp.springboottodoApp.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;

@Repository
public interface TodoRepository extends MongoRepository <Todo,String> {

	Todo findByTitle(String title);
}
