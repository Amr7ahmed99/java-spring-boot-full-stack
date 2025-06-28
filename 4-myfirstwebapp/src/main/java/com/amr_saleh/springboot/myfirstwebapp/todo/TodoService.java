package com.amr_saleh.springboot.myfirstwebapp.todo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService() {
        super();
    }

    public List<Todo> fetchAllTodos(){
        return this.todoRepository.findAll();
    }

    public List<Todo> findByUser(String username) {
        return this.todoRepository.findByUser(username);
        // Predicate<Todo> predicate= todo -> todo.getUser().equalsIgnoreCase(username);
        // return todos.stream().filter(predicate).toList();
    }

    public void addTodo(Todo todo) {
        this.todoRepository.save(todo);
    }

    public Todo findTodoById(int id){
        // Predicate<? super Todo> predicate= todo -> todo.getId() == id;
        // return todos.stream().filter(predicate).findFirst().get();

        // Alternatively, you can use the following line to find by id directly using the repository:
        return this.todoRepository.findById(id).orElse(null);
    }

    public void deleteTodoById(int id) {
        // This is a more functional approach compared to using a for loop.
        // Predicate is a functional interface that represents a single argument function that returns a boolean.
        // is a lambda expression that checks if the todo's id matches the given id.
        // Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        // todos.removeIf(predicate);
        
        // Alternatively, you can use the following line to delete by id directly using the repository:
        this.todoRepository.deleteById(id);
    }

    public void updateTodo(Todo todo) {
        this.todoRepository.findById(todo.getId()).ifPresent(existingTodo -> {
            existingTodo.setUser(todo.getUser());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setTargetDate(todo.getTargetDate());
            existingTodo.setDone(todo.isDone());
            this.todoRepository.save(existingTodo);
        });
    }
}
