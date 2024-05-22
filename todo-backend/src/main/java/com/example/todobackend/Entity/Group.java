package com.example.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Group {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "members", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Member> members = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "todos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Todo> todos = new HashSet<>();
}
