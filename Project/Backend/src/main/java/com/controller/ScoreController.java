package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.PlayService;
import com.service.PlayerScoreDTO;

@RestController
@RequestMapping("/score")
@CrossOrigin(origins = "http://localhost:5174")
public class ScoreController {

    private final PlayService playService;

    public ScoreController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping("/message")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Display of high Scores");
    }

    @GetMapping("/top10")
    public ResponseEntity<?> getTop10Scores() {
        try {
            // Assuming that PlayService has a method to retrieve top 10 scores
            // along with player aliases and challenge titles
            return ResponseEntity.ok(playService.getTop10Scores());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving top 10 scores");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getPlayerScores(@PathVariable Integer userId) {
        try {
            List<PlayerScoreDTO> playerScores = playService.getPlayerScores(userId);
            return ResponseEntity.ok(playerScores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving player scores");
        }
    }
}
