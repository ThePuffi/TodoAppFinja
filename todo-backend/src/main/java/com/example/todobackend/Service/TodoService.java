package com.example.todobackend.Service;

import com.example.todobackend.Entity.Todo;
import com.example.todobackend.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo addTodo(Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(todo.getId());
        if (todoOptional.isPresent()) {
            throw new IllegalArgumentException("Group already exists");
        }
        return todoRepository.save(todo);
    }

    public Todo editTodo(Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(todo.getId());
        if (todoOptional.isPresent()) {
            return todoRepository.save(todo);
        }
        throw new IllegalArgumentException("Todo does not exit");
    }

    public Todo getTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        }
        throw new IllegalArgumentException("Todo does not exit");
    }

    public void deleteTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Todo does not exit");
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
