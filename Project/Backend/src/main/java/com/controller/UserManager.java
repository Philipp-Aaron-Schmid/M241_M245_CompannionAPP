package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.model.User;
import com.repository.PlayRepository;
import com.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage/users")
@CrossOrigin(origins = "http://localhost:5174")
public class UserManager {

    @Autowired
    PasswordEncoder encoder;
    
    private final UserRepository userRepository;
    private final PlayRepository playRepository;

    public UserManager(UserRepository userRepository, PlayRepository playRepository) {
        this.userRepository = userRepository;
        this.playRepository = playRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        // Retrieve all users
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        // Retrieve user by ID
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable int id, @RequestBody User updatedUser) {
        // Retrieve existing user by ID
        return userRepository.findById(id).map(existingUser -> {
            // Update user information
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setAlias(updatedUser.getAlias());

            // Save the updated user
            userRepository.save(existingUser);

            return ResponseEntity.ok("User profile updated successfully");
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateUserProfile(@PathVariable int id, @RequestBody User partialUpdate) {
        // Retrieve existing user by ID
        return userRepository.findById(id).map(existingUser -> {
            // Update only non-null fields
            if (partialUpdate.getUsername() != null) {
                existingUser.setUsername(partialUpdate.getUsername());
            }
            if (partialUpdate.getEmail() != null) {
                existingUser.setEmail(partialUpdate.getEmail());
            }
            if (partialUpdate.getPassword() != null) {
                existingUser.setPassword(encoder.encode(partialUpdate.getPassword()));
            }
            if (partialUpdate.getAlias() != null) {
                existingUser.setAlias(partialUpdate.getAlias());
            }

            // Save the partially updated user
            userRepository.save(existingUser);

            return ResponseEntity.ok("User profile partially updated successfully");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUserAccount(@PathVariable int id) {
        // Check if the user exists
        Optional<User> optionalUser = userRepository.findById(id);
        if (userRepository.existsById(id)) {
            User user = optionalUser.get();
            // Delete the user by ID
            playRepository.deleteByUserfk(user);
            userRepository.deleteById(id);
            return ResponseEntity.ok("User account deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
