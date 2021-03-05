package com.springboot.todoApp.springboottodoApp.business.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;
import com.springboot.todoApp.springboottodoApp.dao.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;


	public  List<Todo> findAll() {
		return todoRepo.findAll();
	}

	public Todo getTodoById(String id) {
		
		return todoRepo.findById(id).get();
	}
	
	public Todo save(Todo todo) {
		 return todoRepo.save(todo);
	}
	
	public void delete(String id) {
       todoRepo.deleteById(id);
	}
}
