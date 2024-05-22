package com.example.todobackend.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Category {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String color;
}
