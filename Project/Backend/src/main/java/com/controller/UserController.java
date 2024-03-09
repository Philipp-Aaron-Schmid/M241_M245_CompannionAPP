package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.TaskResponse;
import com.model.AssignedClass;
import com.model.User;
import com.model.UserTask;
import com.repository.TaskRepository;
import com.repository.UserRepository;
import com.repository.UserTaskRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired UserTaskRepository userTaskRepository;

    // Assuming UserTaskRepository and TaskRepository are autowired as needed

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<TaskResponse>> getUserTasks(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        AssignedClass userClass = user.getAssignedClass();
        int classTimer = userClass.getTimer();
        long id = userTaskRepository.getByUser(userRepository.findById(userId));

        List<TaskResponse> tasks = user.getUserTasks().stream()
                .map(UserTask::getTask)
                .filter(task -> classTimer >= task.getStart())
                .map(task -> new TaskResponse(task, // Assuming TaskResponse constructor accepts Task and status
                        id, user.getUserTasks().stream().anyMatch(ut -> ut.getTask().equals(task) && ut.isStatus())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }
        
    @PostMapping("/toggle/{userTaskId}")
    public ResponseEntity<?> toggleUserTaskStatus(@PathVariable Long userTaskId) {
        @SuppressWarnings("null")
        UserTask userTask = userTaskRepository.findById(userTaskId)
                .orElseThrow(() -> new RuntimeException("UserTask not found"));

        // Toggle the status
        userTask.setStatus(!userTask.isStatus());

        // Save the updated UserTask
        userTaskRepository.save(userTask);

        return ResponseEntity.ok().build();
    }


    
}
