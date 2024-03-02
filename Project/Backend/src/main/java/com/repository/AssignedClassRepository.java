package com.repository;

import com.model.AssignedClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedClassRepository extends JpaRepository<AssignedClass, Long> {
    // Additional methods if needed
}