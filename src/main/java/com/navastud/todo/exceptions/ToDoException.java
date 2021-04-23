package com.navastud.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ToDoException extends RuntimeException {

    public ToDoException(String message) {
        super(message);
    }
}
