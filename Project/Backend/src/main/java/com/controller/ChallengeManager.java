package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Challenge;
import com.repository.ChallengeRepository;
import com.repository.PlayRepository;
import com.service.ChallengeService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/manage")
@CrossOrigin(origins = "http://localhost:5174") //cross origin annotation to specify from where this request mapping can be acessed
public class ChallengeManager {

    private ChallengeRepository challengeRepository;
    private ChallengeService challengeService;
    private PlayRepository playRepository;

    public ChallengeManager(ChallengeRepository challengeRepository, ChallengeService challengeService,
            PlayRepository playRepository) {
        this.challengeRepository = challengeRepository;
        this.challengeService = challengeService;
        this.playRepository = playRepository;
    }
// initial test method still in here for testing purposes
    @GetMapping("/challange")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Manage challanges here");
    }

    @PostMapping("/challange/add")
    public ResponseEntity<String> addChallenge(@RequestBody String challenge) {
        try {
            ChallengeService.saveChallange(challenge);
            return ResponseEntity.status(HttpStatus.CREATED).body("Challenge added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add challenge");
        }
    }

    @DeleteMapping("/challenge/{id}")
    @Transactional //transactional ensure that the deletes follow acid and do not run afoul of entity relations
    public ResponseEntity<String> deleteChallenge(@PathVariable Integer id) {
        try {
            // Check if the challenge with the given ID exists
            Optional<Challenge> challengeOptional = challengeRepository.findById(id);
            if (challengeOptional.isPresent()) {
                Challenge challenge = challengeOptional.get();

                // Delete all associated plays
                playRepository.deleteByChallengefk(challenge);

                // Delete the challenge from the database
                challengeRepository.delete(challenge);

                return ResponseEntity.ok("Challenge and associated plays deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your use case
            return ResponseEntity.status(500).body("Error deleting challenge");
        }
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        try {

            List<Challenge> challenges = challengeService.getAllChallenges();

            if (!challenges.isEmpty()) {
                return ResponseEntity.ok(challenges);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/challenge/{id}/toggle-display")
    public ResponseEntity<String> toggleChallengeDisplay(@PathVariable Integer id) {
        try {
            // Check if the challenge with the given ID exists
            Optional<Challenge> challengeOptional = challengeRepository.findById(id);
            if (challengeOptional.isPresent()) {
                Challenge challenge = challengeOptional.get();

                // Toggle the display status
                challenge.setChallangeDisplay(!challenge.isChallangeDisplay());

                // Save the updated challenge
                challengeRepository.save(challenge);

                return ResponseEntity.ok("Challenge display status toggled successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately based on your use case
            return ResponseEntity.status(500).body("Error toggling challenge display status");
        }
    }

}
