package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchTodoExistsException extends RuntimeException {
    private String message;

    public NoSuchTodoExistsException() {}

    public NoSuchTodoExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
