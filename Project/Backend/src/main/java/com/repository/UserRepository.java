package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 Optional<User> findByUserfirstname(String username);
 Boolean existsByUserfirstname(String username);
 Boolean existsByEmail(String email);

 @Query("SELECT u FROM User u LEFT JOIN FETCH u.userTasks")
 List<User> findAllWithTasks();
Optional<User> findById(Long userId);
}
