package com.example.todobackend.Controller;


import com.example.todobackend.DTO.TodoDTO;
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
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todo) {
        return new ResponseEntity<>(this.todoService.addTodo(todo), HttpStatus.CREATED);
    }
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        return new ResponseEntity<>(this.todoService.getAllTodos(), HttpStatus.FOUND);
    }
    @GetMapping("/findTodoById")
    public ResponseEntity<TodoDTO> findTodoById(@RequestParam Long todoId) {
        return new ResponseEntity<>(this.todoService.findTodoById(todoId), HttpStatus.FOUND);
    }
    @PutMapping("/updateTodo")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todo) {
        return new ResponseEntity<>(this.todoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo")
    public ResponseEntity<HttpStatus> deleteTodo(@RequestBody TodoDTO todo) {
        this.todoService.deleteTodo(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
