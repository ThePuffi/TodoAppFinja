package com.example.todobackend.Repository;

import com.example.todobackend.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t JOIN t.members m WHERE m.id = :memberId")
    List<Todo> findTodosByMemberId(@Param("memberId") long memberId);
}
