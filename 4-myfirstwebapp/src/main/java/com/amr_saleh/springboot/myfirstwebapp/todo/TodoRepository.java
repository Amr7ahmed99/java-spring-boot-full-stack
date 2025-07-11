package com.amr_saleh.springboot.myfirstwebapp.todo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUser(String user);
}