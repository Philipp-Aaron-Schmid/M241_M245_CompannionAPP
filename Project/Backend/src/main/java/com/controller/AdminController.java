package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.model.Task;
import com.model.User;
import com.repository.TaskRepository;
import com.repository.UserRepository;
import com.repository.UserTaskRepository;
import com.service.TaskService;
import com.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    
    // Method do display all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Method to save multiple users
    @PostMapping("/users")
    public ResponseEntity<List<User>> saveUsers(@RequestBody List<User> users) {
        if (users == null || users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User list is null or empty.");
        }
        users.forEach(userService::createUser);
        return ResponseEntity.ok(users);
    }

    // Method to save a single user with null check
    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User object is null.");
        }
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }
    // Method to display all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    // Method to add a new single task with validation
    @PostMapping("/task")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task object is null.");
        }
        taskService.createTask(task);
        return ResponseEntity.ok(task);
    }

    // Method to add tasks in batch with validation
    @PostMapping("/tasks")
    public ResponseEntity<List<Task>> addTasks(@Valid @RequestBody List<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task list is empty.");
        }
        tasks.forEach(taskService::createTask);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/usertask")
    public ResponseEntity<List<User>> getUsersWithTasks() {
        List<User> users = userRepository.findAllWithTasks(); // Custom method to implement
        return ResponseEntity.ok(users);
    }

}

