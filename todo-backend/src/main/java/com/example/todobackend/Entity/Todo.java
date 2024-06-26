package com.example.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private boolean status;

    @NonNull
    private long categoryId;

    @NonNull
    private Date dueDate;

    @NonNull
    private String description;

    @ManyToMany
    @JoinTable(
            name = "member_todo",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    @JsonIgnoreProperties("todos") // Diese Annotation ignoriert die "todos"-Eigenschaft während der Serialisierung, um unendliche Rekursion zu vermeiden.
    private Set<Member> members = new HashSet<>();

}
