package com.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Challenge;
import com.model.Play;
import com.model.User;
import com.repository.ChallengeRepository;
import com.repository.PlayRepository;
import com.repository.UserRepository;
import com.service.PlayService;
import com.service.PlaySubmissionRequest;
import com.service.Scorer;

@RestController
@RequestMapping("/play")
@CrossOrigin(origins = "http://localhost:5174")
public class PlayController {
    private ChallengeRepository challengeRepository;
    private final UserRepository userRepository; // Assuming you have a UserRepository
    private final PlayService playService;
    private CrudRepository<Play, Integer> playRepository;


    public PlayController(ChallengeRepository challengeRepository, UserRepository userRepository,
            PlayService playService,PlayRepository playRepository) {
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
        this.playService = playService;
        this.playRepository = playRepository;
    }

    @GetMapping("/message")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("let'splay a game!");
    }

    @GetMapping("/load")
    public ResponseEntity<Map<String, Object>> playChallenge(@RequestParam("userId") int userId,
            @RequestParam("challengeId") int challengeId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);

        if (optionalUser.isPresent() && optionalChallenge.isPresent()) {
            User user = optionalUser.get();
            Challenge challenge = optionalChallenge.get();

            Play newPlay = playService.createPlayEntry(user, challenge); // Implement this method in your PlayService

            Map<String, Object> response = new HashMap<>();
            response.put("challengeId", challenge.getChallangeId());
            response.put("challengeSet", challenge.getChallangeSet());
            response.put("challengeTime", challenge.getChallangeTime());
            response.put("playId", newPlay.getPlay_id());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

   @PostMapping("/submit")
public ResponseEntity<String> submitPlay(@RequestBody PlaySubmissionRequest playSubmissionRequest) {
    Optional<Play> optionalPlay = playRepository.findById(playSubmissionRequest.getPlayId());

    if (optionalPlay.isPresent()) {
        Play play = optionalPlay.get();
        Challenge challenge = play.getChallengefk();
        // Assuming PlaySubmissionRequest has fields playSet and playTime
        play.setPlaySet(playSubmissionRequest.getPlaySet());
        play.setPlayTime(playSubmissionRequest.getPlayTime());

        Scorer.calculateScoreAndSet(challenge, play);

        playRepository.save(play);

        return ResponseEntity.ok("Play updated successfully Score:"+play.getPlayScore());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Play not found");
    }
}
}