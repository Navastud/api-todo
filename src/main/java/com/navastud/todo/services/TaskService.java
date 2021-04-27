package com.navastud.todo.services;

import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.models.TaskDTO;
import org.springframework.dao.EmptyResultDataAccessException;

public interface TaskService {

    /**
     * Create a new Task
     *
     * @param task Task DTO
     * @return String
     * @throws ToDoException when Task already exist
     */
    String createTask(TaskDTO task) throws ToDoException;

    /**
     * Update Task DTO
     *
     * @param task Task DTO
     * @throws ToDoException when Task doesn't exist
     */
    String updateTask(TaskDTO task) throws ToDoException;

    /**
     * Delete a Task existing
     *
     * @param id ID Task
     * @return String
     * @throws EmptyResultDataAccessException when Task doesn't exist
     */
    String deleteTask(Long id);
}
