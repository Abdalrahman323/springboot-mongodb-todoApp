package com.springboot.todoApp.springboottodoApp.business.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.todoApp.springboottodoApp.AbstractTodoAppTest;
import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

public class TodoControllerTest extends AbstractTodoAppTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  TodoService todoService;

    @Test  // get_todo
    public  void whenGetAllTodos_thenReturnJsonArray() throws Exception {
        Todo todo1 = new Todo("1", "Todo 1", "Todo 1");
        Todo todo2 = new Todo("2", "Todo 2", "Todo 2");
        List<Todo> data = Arrays.asList(todo1, todo2);

        given(todoService.findByUser(anyString())).willReturn(data);


        mockMvc.perform(doGet("/api/v1/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
    }

    @Test // post_todo
    public void whenPostTodo_thenCreateTodo() throws Exception {
        Todo todo1=new Todo("1","todo1 ","todo1 description");

       given(todoService.save(Mockito.any(Todo.class))).willReturn(todo1);

        ObjectMapper objectMapper = new ObjectMapper();
       mockMvc.perform(
               doPost("/api/v1/todos")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(todo1)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title",is(todo1.getTitle())));
    }
}
