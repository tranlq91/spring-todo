package com.example.demo.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TodoDTO implements Serializable {
    public String name;

    public boolean isChecked;
}
