package com.navastud.todo.services.impl;

import com.navastud.todo.entities.TaskEntity;
import com.navastud.todo.entities.UserEntity;
import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void createTask_Correctly() {
        UserEntity user = UserEntity.builder().id(1L).username("roberto").build();
        TaskEntity task = TaskEntity.builder().title("Example Task").description("Lorem ipsum").user(user).build();
        assertEquals("Task created successfully", taskServiceImpl.createTask(task));
    }

    @Test
    void createTask_ThrowException_WhenTaskHaveId() {
        UserEntity user = UserEntity.builder().id(1L).username("roberto").build();
        TaskEntity task = TaskEntity.builder().id(1L).title("Example Task").description("Lorem ipsum").user(user).build();
        ToDoException toDoException = assertThrows(ToDoException.class, () -> taskServiceImpl.createTask(task));
        assertEquals("Task already exist!", toDoException.getMessage());
    }


}