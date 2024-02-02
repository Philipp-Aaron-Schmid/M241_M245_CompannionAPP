package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "http://localhost:5174")
public class IndexController {

    @GetMapping("/index")
    public ResponseEntity<List<String>> getGreeting() {
        return ResponseEntity.ok(List.of("Competitive Sudoku elevates the traditional puzzle-solving experience into a thrilling race against the clock, where every second counts. The essence of this challenge lies not just in solving the puzzles, but in doing so as quickly and accurately as possible. Imagine the adrenaline rush as you work through the grids, your mind racing to find the right numbers while the timer ticks down. This time pressure adds a whole new layer of excitement to Sudoku, transforming it from a tranquil pastime into an intense, edge-of-your-seat adventure. Each game becomes a test of not only logic and skill but also speed and decision-making under pressure. Whether you’re a seasoned Sudoku enthusiast or new to the world of competitive puzzles, the ticking clock is an ever-present reminder that every moment is valuable, pushing you to sharpen your skills and improve your strategies with each game.", "The competitive aspect of Sudoku also introduces the thrill of comparison and social interaction, adding a vibrant community dimension to the game. As you complete each puzzle, you can’t help but wonder how you stack up against others who share your passion. Leaderboards and live competitions provide an exhilarating platform to measure your skills against fellow enthusiasts, from casual players to seasoned experts. Each name on the leaderboard tells a story of strategy, speed, and sharp wits. In this dynamic environment, Sudoku transcends its solitary roots, becoming a shared journey of continuous improvement and friendly rivalry. The joy of seeing your name rise in the rankings, or the motivation that comes from aspiring to reach the heights of top players, adds a deeply satisfying and social aspect to the competitive Sudoku experience.", "Perhaps the most intriguing aspect of competitive Sudoku is the strategic shift it demands. Unlike traditional Sudoku, where the goal is to complete each puzzle, competitive Sudoku challenges players to achieve as much as they can within the time limit. This paradigm shift turns every puzzle into a strategic battleground, where players must weigh the value of attempting to solve a challenging grid against the ticking clock. Scoring in competitive Sudoku is quick and intuitive, allowing players to immediately reflect on their performance. They can easily identify past mistakes and learn from them, constantly evolving their approach and strategies. However, the format is unforgiving—there are no second chances or retries. Once the clock runs out, the results are final. This unyielding structure makes every success more rewarding and every missed opportunity a lesson for future games. Competitive Sudoku is not just about solving puzzles; it's about mastering the art of quick thinking, efficient problem-solving, and tactical planning."));
    }
}
