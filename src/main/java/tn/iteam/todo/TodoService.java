package tn.iteam.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount=0;

    static {
        todos.add(new Todo(++todosCount, "iteam", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "iteam", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "iteam", "Learn Full Stack Development",
                LocalDate.now().plusYears(3), false));
    }

    // Adding Todo to the DB
    public void addTodo(String username, String decription, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount,username, decription, targetDate, done );
        todos.add(todo);
    }

    // Removing Todo from the DB
    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;
        todos.removeIf(predicate);
    }


    // Retrieving List Todos from the DB
    public  List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    // Retrieve by Id from DB
    public Todo findById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    // Updating todo from the DB
    public void updateTodo(Todo todo){
        deleteById(todo.getId());
        todos.add(todo);
    }


}
