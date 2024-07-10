package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoValidFieldException extends RuntimeException {
    private String message;

    public NoValidFieldException() {}

    public NoValidFieldException(String msg) {
        super(msg);
        this.message = msg;
    }
}
