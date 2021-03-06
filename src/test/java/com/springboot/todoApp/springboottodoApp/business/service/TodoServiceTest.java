package com.springboot.todoApp.springboottodoApp.business.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.springboot.todoApp.springboottodoApp.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;
import com.springboot.todoApp.springboottodoApp.dao.repository.TodoRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class TodoServiceTest {
	
	@MockBean
	private TodoRepository todoRepository;

	@Autowired
	private TodoService todoService;
	
	
	@TestConfiguration   // Auto Injection
	static class TodoServiceContextConfiguration {
		@Bean
		public TodoService todoService() {
			return new TodoService();
		}
	}
	
	@Test
	public void whenFindall_ReturnTodoList() {
	
		//Mockup
		
		Todo todo1=new Todo("1","todo1 ","todo1 description");
		Todo todo2=new Todo("2","todo2 ","todo1 description");

		List<Todo> data = Arrays.asList(todo1,todo2);
		
		given(this.todoRepository.findAll()).willReturn(data);
		//
		
		assertThat(this.todoService.findAll())
		.hasSize(2)
		.contains(todo1,todo2); 
		
	}
	
	@Test
	public void  whenGetById_TodoShouldBeFound() {
		Todo todo1=new Todo("1","todo1 ","todo1 description");

		given(todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todo1));

		Todo result = todoService.getTodoById("1");
		assertThat(result.getTitle()).containsIgnoringCase("todo");
	}

	@Test(expected = NotFoundException.class)
	public  void whenInvalidId_TodoShouldBeNotFound(){
		given(todoRepository.findById(anyString())).willReturn(Optional.empty());

		todoService.getTodoById("1");

	}
}
