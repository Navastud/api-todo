package com.navastud.todo.services.impl;

import com.navastud.todo.entities.TaskEntity;
import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.models.TaskDTO;
import com.navastud.todo.repositories.TaskRepository;
import com.navastud.todo.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Qualifier("TaskRepository")
    private TaskRepository taskRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createTask(TaskDTO task) throws ToDoException {
        if (Objects.nonNull(task.getId()))
            throw new ToDoException("Task already exist!");

        taskRepository.save(modelMapper.map(task, TaskEntity.class));
        return "Task created successfully";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateTask(TaskDTO task) throws ToDoException {
        TaskEntity taskEntity = taskRepository.findById(task.getId())
                .orElseThrow(() -> new ToDoException("Task with ID:" + task.getId() + " doesn't exist!"));

        taskEntity.setDescription(task.getDescription());
        taskEntity.setTitle(task.getTitle());

        taskRepository.save(taskEntity);

        return "Task updated successfully";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "Task with ID: " + id + " has been deleted successfully!";
    }

}
