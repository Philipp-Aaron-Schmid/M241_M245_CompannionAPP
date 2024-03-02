package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Task;
import com.model.User;
import com.model.UserTask;
import com.repository.TaskRepository;
import com.repository.UserRepository;
import com.repository.UserTaskRepository;

@Service
public class TaskService {
@Autowired
private UserRepository userRepository;
@Autowired
private TaskRepository taskRepository;

@Autowired
private UserTaskRepository userTaskRepository;

@SuppressWarnings("null")
public void createTask(Task task) {
    taskRepository.save(task); // Save the new task
    List<User> users = userRepository.findAll(); // Retrieve all users
    // Create UserTask for each user
    users.forEach(user -> {
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setStatus(false); // Assuming default status is false
        userTaskRepository.save(userTask);
    });
}

}
