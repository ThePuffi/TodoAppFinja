package com.example.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String username;

    private String firstname;

    private String lastname;

    private String password;

    private String eMail;

    @ManyToMany(mappedBy = "members")
    @JsonIgnoreProperties("members") // Diese Annotation ignoriert die "members"-Eigenschaft während der Serialisierung, um unendliche Rekursion zu vermeiden.
    private Set<Todo> todos = new HashSet<>();

}
