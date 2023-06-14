package com.emmanueldonkor.TodoApi.repository;

import com.emmanueldonkor.TodoApi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
