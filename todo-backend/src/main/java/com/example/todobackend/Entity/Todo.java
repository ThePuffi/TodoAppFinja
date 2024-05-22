package com.example.todobackend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

public class Todo {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private boolean status;

    private Member member;

    private long groupId;

    private long categoryId;

    private Date dueDate;

    private String description;
}
