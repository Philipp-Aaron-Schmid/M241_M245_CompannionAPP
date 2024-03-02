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
public class UserService {

@Autowired
private UserRepository userRepository;

@Autowired
private TaskRepository taskRepository;

@Autowired
private UserTaskRepository userTaskRepository;


@SuppressWarnings("null")
public void createUser(User user) {
    userRepository.save(user); // Save the new user
    List<Task> tasks = taskRepository.findAll(); // Retrieve all tasks
    // Create UserTask for each task
    tasks.forEach(task -> {
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setStatus(false); // Assuming default status is false
        userTaskRepository.save(userTask);
    });
}

}
