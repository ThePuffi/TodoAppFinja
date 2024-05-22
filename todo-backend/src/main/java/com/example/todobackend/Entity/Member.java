package com.example.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class Member {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String eMail;


}
