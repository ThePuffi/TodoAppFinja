package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Todo;
import com.example.todobackend.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("api/todo")
@CrossOrigin
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/addTodo")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(this.todoService.addTodo(todo), HttpStatus.CREATED);
    }
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(this.todoService.getAllTodos(), HttpStatus.FOUND);
    }
    @GetMapping("/findTodoById")
    public ResponseEntity<Todo> findTodoById(@RequestParam Long todoId) {
        return new ResponseEntity<>(this.todoService.getTodo(todoId), HttpStatus.FOUND);
    }
    @PutMapping("/updateTodo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(this.todoService.editTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo")
    public ResponseEntity<HttpStatus> deleteTodo(@RequestBody long todoId) {
        this.todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
