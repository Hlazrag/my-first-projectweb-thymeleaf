package tn.iteam.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {

    private  final TodoService todoService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // GET
    @RequestMapping(value = "list-todos")
    public String listAllTodos(ModelMap model){
        String username = (String) model.get("name");
        logger.info("The username from SessionAttribute is {}", username);
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }


    // GET MAPPING
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model){
        model.addAttribute("todo", new Todo());
        return "todo";
    }

    // POST MAPPING
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        String username = getLoggedInUsername();
        if(result.hasErrors())
            return "todo";
        todoService.addTodo(username,
                todo.getDescription(),
                LocalDate.now().plusYears(1), false);
                //todo.getTargetDate(), false);
        System.out.println(todo.getDescription());
        System.out.println(todo.getId());
        return "redirect:list-todos";
    }

    // DELETE MAPPING
    @RequestMapping(value = "delete-todo")
    public String deleteTdo(@RequestParam int id){
        // delete todo
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    // UPDATE-GET
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTdoPage (@RequestParam int id, ModelMap model){
        // retrieve todo
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    // UPDATE-POST
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTdo(@Valid Todo todo, ModelMap model,  BindingResult result){
        if(result.hasErrors())
            return "todo";
        String username = (String) model.get("name");
        if(result.hasErrors())
            return "todo";
        todo.setUsername(username);
        todoService.updateTodo(todo);
        model.addAttribute("todo", todo);
        return "redirect:list-todos";
    }

    // Getting userName from SpringSecurity

    private String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }


}

