package com.repository;

import com.model.User;
import com.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    // Find UserTasks by userId, assuming there's a User entity related to UserTask with a 'user' field
    List<UserTask> findByUser(User user);

    List<UserTask> findByUserId(Long userId);

    Optional<User> findByUserIdAndTaskId(Long userId, Long taskId);

    long getByUser(Optional<User> byId);
}
