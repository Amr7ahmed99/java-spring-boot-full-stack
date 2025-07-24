package com.fullstack.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private List<Todo> todos;
	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> findByUsername(String username){
		return todoRepository.findByUsername(username);
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo();
		todo.setUsername(username);
		todo.setDescription(description);
		todo.setTargetDate(targetDate);
		todo.setDone(done);
		return this.todoRepository.save(todo);
	}
	
	public void deleteById(int id) {
		this.todoRepository.deleteById(id);
	}

	public Todo findById(int id) {
		return this.todoRepository.findById(id).orElse(null);
	}

	public void updateTodo(Todo todo) {
		this.todoRepository.save(todo);
	}
}