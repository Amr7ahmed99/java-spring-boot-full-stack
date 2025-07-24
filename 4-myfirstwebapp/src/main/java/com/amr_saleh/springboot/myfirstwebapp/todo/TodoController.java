package com.amr_saleh.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@SessionAttributes("username")
public class TodoController {
    
    private TodoService todoService;
    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }


    @RequestMapping(value="list-todos", method=RequestMethod.GET)
    private String listTodos(ModelMap model) {
        List<Todo> todos= this.todoService.findByUser(this.getLoggedInUsername());
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value= "add-todo", method=RequestMethod.GET)
    private String showAddNewTodoPage(ModelMap model) {
        Todo todo = new Todo(0, "", false, LocalDate.now().toString(), this.getLoggedInUsername());
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value= "add-todo", method=RequestMethod.POST)
    private String addNewTodo(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        this.todoService.addTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo", method=RequestMethod.GET)
    private String deleteTodo(@RequestParam int id) {
        this.todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }
    
    @RequestMapping(value = "update-todo", method=RequestMethod.GET)
    private String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo= this.todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "updateTodo";
    }

    @RequestMapping(value= "update-todo", method=RequestMethod.POST)
    private String updateTodo(@Valid Todo todo, BindingResult result){
         if (result.hasErrors()) {
            return "updateTodo";
        }
        this.todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername(){
       Authentication auth= SecurityContextHolder.getContext().getAuthentication();
       return auth.getName();
    }
}
