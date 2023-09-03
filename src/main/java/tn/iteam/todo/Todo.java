package tn.iteam.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,generator = "todo_generator")
    @SequenceGenerator
            (name="todo_generator", sequenceName = "todo_seq",
                    initialValue = 10005,
                    allocationSize=50)
    private int id;
    private String username;
    @Size(min = 10, message = "Size must be greater than 10")
    private String description;
    private LocalDate targetDate;
    private boolean done;

}
