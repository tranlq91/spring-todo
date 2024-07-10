package com.example.demo.controller;

import com.example.demo.entity.Todo;
import com.example.demo.model.TodoDTO;
import com.example.demo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(
    path = "/v1/todo",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class TodoListController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/name")
    public List<Todo> getTodosByName(@RequestParam(value = "name") String name) {
        return todoService.getTodosByName(name).orElseThrow(
                () -> new NoSuchElementException("NO TODO ITEM PRESENT WITH Name = " + name));
    }

    @GetMapping("/{id}")
    public Todo getTodosById(@PathVariable(name = "id") Long id) {
        return todoService.getTodosById(id).orElseThrow(
                () -> new NoSuchElementException("NO TODO ITEM PRESENT WITH Id = " + id));
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseEntity<Todo> createNewTodo(@RequestBody TodoDTO todoDTO, HttpServletRequest request) {
        Todo todo = todoService.createNewTodo(todoDTO);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(name = "id") Long id, @RequestBody TodoDTO todo) {
        return ResponseEntity.ok(todoService.updateTodo(id ,todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTodo(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

}
