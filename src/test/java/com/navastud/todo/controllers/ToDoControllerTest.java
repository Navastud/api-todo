package com.navastud.todo.controllers;


import com.google.gson.Gson;
import com.navastud.todo.models.TaskDTO;
import com.navastud.todo.services.impl.TaskServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class ToDoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    @SneakyThrows
    void taskCreate_Successfully() {
        TaskDTO task = TaskDTO.builder().title("Example Task").description("Lorem ipsum").user("roberto").build();

        when(taskService.createTask(any())).thenReturn("Task created successfully");
        MockHttpServletRequestBuilder request = post("/api/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(task));
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(result -> assertEquals("Task created successfully", result.getResponse().getContentAsString()));
    }


}
