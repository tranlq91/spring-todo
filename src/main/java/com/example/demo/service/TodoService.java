package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Todo;
import com.example.demo.exception.NoSuchTodoExistsException;
import com.example.demo.exception.NoValidFieldException;
import com.example.demo.model.TodoDTO;
import com.example.demo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {

    private static final Logger log = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<List<Todo>> getTodosByName(String name) {
        log.info("Finding todo with name: " + name);
        return todoRepository.findByName(name);
    }

    public Optional<Todo> getTodosById(Long id) {
        log.info("Finding todo with id: " + id);
        return todoRepository.findById(id);
    }

    @Transactional
    public Todo createNewTodo(TodoDTO todoDTO) {
        log.info("Creating a new todo with name: " + todoDTO.name);
        validationTodoFields(todoDTO);
        Todo todo = new Todo(todoDTO.name, todoDTO.isChecked);
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Long id, TodoDTO todoDTO) {
        log.info("Updating todo with id: " + id);
        Optional<Todo> todoOptional = todoRepository.findById(id);
        validationTodoExist(id);
        Todo todo = todoOptional.get();
        todo.setChecked(todoDTO.isChecked);
        todo.setName(todoDTO.name);
        todoRepository.save(todo);
        return todo;

    }

    @Transactional
    public Long deleteTodo(Long id) {
        log.info("Deleting todo with id: " + id);
        validationTodoExist(id);
        todoRepository.deleteById(id);
        return id;
    }

    private void validationTodoExist(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new NoSuchTodoExistsException("No Such Todo exists!!");
        }
    }

    private void validationTodoFields(TodoDTO todoDTO) {
        if (Objects.equals(todoDTO.name, "") || todoDTO.name == null) {
            throw new NoValidFieldException("The todo name is invalid!!");
        }
    }
}
