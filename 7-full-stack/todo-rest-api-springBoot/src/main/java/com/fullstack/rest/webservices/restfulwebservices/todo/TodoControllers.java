package com.fullstack.rest.webservices.restfulwebservices.todo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class TodoControllers {

    @Autowired
    private TodoService todoService;

    @GetMapping("/{username}/todos")
    public List<Todo> findTodoBy(@PathVariable String username) {
        List<Todo> userTodos= this.todoService.findByUsername(username); 
        return userTodos;
    }

    @GetMapping("/{username}/todos/{id}")
    public ResponseEntity<Todo> findTodoBy(@PathVariable String username, @PathVariable Integer id) {
        Todo todo= this.todoService.findById(id); 
        return todo == null? ResponseEntity.notFound().build(): ResponseEntity.ok(todo);
    }

    @PostMapping("/{username}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        this.todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());        
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable Integer id){
        this.todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/todos/{id}")
    public ResponseEntity<Void> updateTodoById(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo) {
        todo.setId(id);
        todo.setUsername(username);
        this.todoService.updateTodo(todo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/basic-auth")
    public String basicAuthLogin() {
        return "success";
    }
    
    
    
}
