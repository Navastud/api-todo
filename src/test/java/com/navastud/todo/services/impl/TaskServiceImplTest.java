package com.navastud.todo.services.impl;

import com.navastud.todo.entities.TaskEntity;
import com.navastud.todo.entities.UserEntity;
import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.models.TaskDTO;
import com.navastud.todo.repositories.TaskRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    @SneakyThrows
    void createTask_Successfully() {
        TaskDTO task = TaskDTO.builder().title("Example Task").description("Lorem ipsum").user("roberto").build();
        assertEquals("Task created successfully", taskServiceImpl.createTask(task));
    }

    @Test
    void createTask_ThrowException_WhenTaskHaveId() {
        TaskDTO task = TaskDTO.builder().id(1L).title("Example Task").description("Lorem ipsum").user("roberto").build();
        ToDoException toDoException = assertThrows(ToDoException.class, () -> taskServiceImpl.createTask(task));
        assertEquals("Task already exist!", toDoException.getMessage());
    }


    @Test
    @SneakyThrows
    void updateTask_Successfully() {
        TaskDTO task = TaskDTO.builder().id(1L).title("Example Task").description("Lorem ipsum").user("julio").build();
        UserEntity user = UserEntity.builder().id(1L).username("julio").build();
        TaskEntity taskEntity = TaskEntity.builder().id(1L).title("Example Task").description("Lorem ipsum").user(user).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        assertEquals("Task updated successfully", taskServiceImpl.updateTask(task));
    }

    @Test
    void updateTask_ThrowException_WhenTaskDoesntExist() {
        TaskDTO task = TaskDTO.builder().id(1L).title("Example Task").description("Lorem ipsum").user("roberto").build();
        ToDoException toDoException = assertThrows(ToDoException.class, () -> taskServiceImpl.updateTask(task));
        assertEquals("Task with ID:1 doesn't exist!", toDoException.getMessage());
    }

    @Test
    @SneakyThrows
    void deleteTask_Successfully() {
        assertEquals("Task with ID: 1 has been deleted successfully!", taskServiceImpl.deleteTask(1L));
    }

    @Test
    void deleteTask_ThrowException_WhenTaskDoesntExist() {
        doThrow(new EmptyResultDataAccessException("No TaskEntity entity with id 1 exists!", 1))
                .when(taskRepository).deleteById(anyLong());
        EmptyResultDataAccessException exception = assertThrows(EmptyResultDataAccessException.class, () -> taskServiceImpl.deleteTask(1L));
        assertEquals("No TaskEntity entity with id 1 exists!", exception.getMessage());
    }

}