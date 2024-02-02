package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.model.Challenge;
import com.model.Play;
import com.model.User;
import com.repository.ChallengeRepository;
import com.repository.PlayRepository;
import com.repository.UserRepository;

import lombok.Data;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:5174")
public class ChallengeController {
    private final ChallengeRepository challengeRepository;
    private PlayRepository playRepository;
    private UserRepository userRepository;
    

    public ChallengeController(ChallengeRepository challengeRepository, PlayRepository playRepository,
            UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.playRepository = playRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/challange")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("challanges here");
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ChallengeResponseDTO>> getAllChallenges() {
        Set<ChallengeResponseDTO> challenges = challengeRepository.findByChallangeDisplayTrue()
                .stream()
                .map(challenge -> new ChallengeResponseDTO(
                        challenge.getChallangeId(),
                        challenge.getChallangeTitle(),
                        challenge.getChallangeTime()))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/byUser")
public ResponseEntity<Set<ChallengeResponseDTO>> getUserChallanges(@RequestParam Integer userId) {
        // Retrieve authenticated user's username
    String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

    // Retrieve user by username
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        // Check if the authenticated user matches the requested user
        if (!user.getUsername().equals(authenticatedUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }}
    User user = optionalUser.get();
    Set<ChallengeResponseDTO> challenges = challengeRepository.findByChallangeDisplayTrue()
            .stream()
            .filter(challenge -> !hasUserPlayedChallenge(user, challenge))
            .map(challenge -> new ChallengeResponseDTO(
                    challenge.getChallangeId(),
                    challenge.getChallangeTitle(),
                    challenge.getChallangeTime()
            ))
            .collect(Collectors.toSet());

    return ResponseEntity.ok(challenges);

}

    private boolean hasUserPlayedChallenge(User user, Challenge challenge) {
        List<Play> plays = playRepository.findByUserfkAndChallengefk(user, challenge);
        return !plays.isEmpty();
    }}

@Data
class ChallengeResponseDTO { //inner class response model

    private Integer challangeId;
    private String challangeTitle;
    private int challangeTime;


    public ChallengeResponseDTO(Integer challangeId, String challangeTitle, int challangeTime) {
        this.challangeId = challangeId;
        this.challangeTitle = challangeTitle;
        this.challangeTime = challangeTime;
    }

}