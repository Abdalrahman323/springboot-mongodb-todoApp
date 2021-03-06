package com.springboot.todoApp.springboottodoApp.business.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;
import com.springboot.todoApp.springboottodoApp.dao.repository.TodoRepository;
import com.springboot.todoApp.springboottodoApp.exceptions.ConflictException;
import com.springboot.todoApp.springboottodoApp.exceptions.NotFoundException;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;


	public  List<Todo> findAll() {
		return todoRepo.findAll();
	}

	public Todo getTodoById(String id) {
		
		try {
			return todoRepo.findById(id).get();			
		} catch(NoSuchElementException ex) {
			throw new NotFoundException(String.format("No Record with the id [%s] in the database" , id));
		}
	}
	
	public Todo save(Todo todo) {
		if(todoRepo.findByTitle(todo.getTitle()) != null){	
			throw new ConflictException("there was todo with title exist");
		}
		
		 return todoRepo.save(todo);
	}
	
	public void delete(String id) {
       todoRepo.deleteById(id);
	}
}
