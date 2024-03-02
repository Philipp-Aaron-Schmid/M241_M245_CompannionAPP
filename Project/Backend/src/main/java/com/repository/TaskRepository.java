package com.repository;

import com.model.Task; // Import your Task model
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // This interface now allows basic CRUD operations on the Task entity
}

