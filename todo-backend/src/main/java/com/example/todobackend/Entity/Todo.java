package com.example.todobackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private boolean status;

    private long groupId;

    private long categoryId;

    private Date dueDate;

    private String description;

}
