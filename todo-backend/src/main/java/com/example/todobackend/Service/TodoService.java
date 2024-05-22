package com.example.todobackend.Service;

import com.example.todobackend.DTO.TodoDTO;
import com.example.todobackend.Entity.Group;
import com.example.todobackend.Entity.Todo;
import com.example.todobackend.Mapper.TodoMapper;
import com.example.todobackend.Repository.GroupRepository;
import com.example.todobackend.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final GroupRepository groupRepository;
    private final TodoMapper todoMapper;

    private final String doesNotExist = "Todo does not exit";

    public TodoDTO addTodo(Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(todo.getId());
        Optional<Group> groupOptional = groupRepository.findById(todo.getGroup().getId());
        if (todoOptional.isPresent() || groupOptional.isEmpty()) {
            throw new IllegalArgumentException("Group already exists");
        }
        return todoMapper.entityToDto(todoRepository.save(todo));
    }

    public TodoDTO editTodo(Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(todo.getId());
        Optional<Group> groupOptional = groupRepository.findById(todo.getGroup().getId());
        if (todoOptional.isPresent() && groupOptional.isPresent()) {
            return todoMapper.entityToDto(todoRepository.save(todo));
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public TodoDTO getTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoMapper.entityToDto(todoOptional.get());
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public void deleteTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException(doesNotExist);
    }

    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = new ArrayList<>();
        List<Todo> todosEntity = todoRepository.findAll();
        todosEntity.forEach(todo -> {
            todos.add(todoMapper.entityToDto(todo));
        });
        return todos;
    }
}
