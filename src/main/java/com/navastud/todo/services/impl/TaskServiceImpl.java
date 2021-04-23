package com.navastud.todo.services.impl;

import com.navastud.todo.entities.TaskEntity;
import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.repositories.TaskRepository;
import com.navastud.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Qualifier("TaskRepository")
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String createTask(TaskEntity task) {

        if (Objects.nonNull(task.getId()))
            throw new ToDoException("Task already exist!");

        taskRepository.save(task);
        return "Task created successfully";
    }

}
