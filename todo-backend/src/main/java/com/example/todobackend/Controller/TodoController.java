package com.example.todobackend.Controller;


import com.example.todobackend.Entity.Member;
import com.example.todobackend.Entity.Todo;
import com.example.todobackend.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class  TodoController {

    private final TodoService todoService;

    @PostMapping("/addTodo")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(this.todoService.addTodo(todo), HttpStatus.CREATED);
    }
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodosByMemberId(@RequestParam("MemberId") long memberId) {
        return new ResponseEntity<>(this.todoService.getAllTodos(memberId), HttpStatus.OK);
    }
    @GetMapping("/findTodoById")
    public ResponseEntity<Todo> findTodoById(@RequestParam("TodoId") Long todoId) {
        return new ResponseEntity<>(this.todoService.getTodo(todoId), HttpStatus.OK);
    }
    @PutMapping("/updateTodo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(this.todoService.editTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo")
    public ResponseEntity<HttpStatus> deleteTodo(@RequestParam("TodoId") long todoId) {
        this.todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
