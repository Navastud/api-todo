package com.navastud.todo.controllers;

import com.navastud.todo.exceptions.ToDoException;
import com.navastud.todo.models.TaskDTO;
import com.navastud.todo.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Qualifier("TaskServiceImpl")
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping("/task")
    ResponseEntity<String> createTask(@RequestBody TaskDTO task) throws ToDoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskServiceImpl.createTask(task));
    }

}
