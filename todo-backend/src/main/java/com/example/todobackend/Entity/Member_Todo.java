package com.example.todobackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member_Todo {

    @Id
    @GeneratedValue
    private long id;

    private long memberId;

    private long todoId;

}
