package com.springboot.todoApp.springboottodoApp.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity< List<Todo> > listTodos() {
		List<Todo> allTodos =  todoservice.findAll(); 
		return new ResponseEntity<>(allTodos,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable String id) {
		Todo resultTodo = todoservice.getTodoById(id);
		
		return new ResponseEntity<>(resultTodo,HttpStatus.OK);
	}
	
	@PostMapping(value= {"" , "/"})
	public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo todo) {
		
		Todo savedTodo = todoservice.save(todo);
		
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}

	@DeleteMapping(value= "{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
		todoservice.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
