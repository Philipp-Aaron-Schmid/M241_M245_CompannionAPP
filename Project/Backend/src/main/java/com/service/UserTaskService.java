package com.service;

import java.util.ArrayList;
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
public class UserTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    public void createUserTasksForNewTask(Task newTask) {
        List<User> allUsers = userRepository.findAll(); // Fetch all users
        List<UserTask> userTasks = new ArrayList<>();

        for (User user : allUsers) {
            UserTask userTask = new UserTask();
            userTask.setUser(user);
            userTask.setTask(newTask);
            userTask.setStatus(false); // Assuming default status is false
            userTasks.add(userTask);
        }

        userTaskRepository.saveAll(userTasks); // Save all UserTask entities
    }
}