package com.navastud.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ToDoException extends Exception {

    public ToDoException(String message) {
        super(message);
    }
}
