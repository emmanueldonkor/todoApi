package com.emmanueldonkor.TodoApi.controller;

import com.emmanueldonkor.TodoApi.entity.Todo;
import com.emmanueldonkor.TodoApi.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
  private final TodoRepository todoRepository;
  public TodoController(TodoRepository todoRepository){
    this.todoRepository = todoRepository;
  }

  @PostMapping
  public Todo createTodo(@RequestBody Todo todo){
    return todoRepository.save(todo);
  }

  @GetMapping
  public List<Todo> getAllTodos(){
    return todoRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
    Optional<Todo> todo = todoRepository.findById(id);
    return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo){
    Optional<Todo> todo = todoRepository.findById(id);
    if(todo.isPresent()){
      updatedTodo.setId(id);
      todoRepository.save(updatedTodo);
      return ResponseEntity.ok(updatedTodo);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTod(@PathVariable Long id){
    Optional<Todo> todo = todoRepository.findById(id);
    if(todo.isPresent()){
      todoRepository.delete(todo.get());
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
