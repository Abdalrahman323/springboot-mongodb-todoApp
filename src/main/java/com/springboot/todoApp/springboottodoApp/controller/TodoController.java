package com.springboot.todoApp.springboottodoApp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todoApp.springboottodoApp.business.service.TodoService;
import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {
	
	@Autowired
    private TodoService todoservice ; 
    
	@GetMapping(value = {"","/"})
	public List<Todo> listTodos() {
		return todoservice.findAll(); 
	}
	
	@GetMapping("{id}")
	public Todo getTodoById(@PathVariable String id) {
		return todoservice.getTodoById(id);
	}
	
	@PostMapping(value= {"" , "/"})
	public Todo createNewTodo(@RequestBody Todo todo) {
		
		return todoservice.save(todo);
	}

	@DeleteMapping(value= "{id}")
	public void deleteTodo(@PathVariable String id) {
		todoservice.delete(id);
	}
}
