package com.springboot.todoApp.springboottodoApp.business.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.springboot.todoApp.springboottodoApp.dao.entity.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc  // simulate sending requests
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  TodoService todoService;
    @Test  // get
    public  void whenGetAllTodos_thenReturnJsonArray() throws Exception {
        Todo todo1=new Todo("1","todo1 ","todo1 description");
        Todo todo2=new Todo("2","todo2 ","todo1 description");
        List<Todo> data = Arrays.asList(todo1,todo2);

        given(todoService.findAll()).willReturn(data);
        mockMvc.perform(get("/api/v1/todos").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$",hasSize(2)))
         .andExpect(jsonPath("$[0].title",equalTo(todo1.getTitle())));
    }

    @Test
    public void whenPostTodo_thenCreateTodo() throws Exception {
        Todo todo1=new Todo("1","todo1 ","todo1 description");

       given(todoService.save(Mockito.any(Todo.class))).willReturn(todo1);

        ObjectMapper objectMapper = new ObjectMapper();
       mockMvc.perform(
               post("/api/v1/todos")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(todo1)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title",is(todo1.getTitle())));
    }
}
