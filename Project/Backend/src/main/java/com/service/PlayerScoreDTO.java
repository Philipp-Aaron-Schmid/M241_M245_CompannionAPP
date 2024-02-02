package com.service;

import lombok.Data;

@Data
public class PlayerScoreDTO {

    private int id;
    private String playSet;
    private String playsScoreSet;
    private int score;
    private String challengeTitle;

    public PlayerScoreDTO(int id, String playSet, String playsScoreSet, int score, String challengeTitle) {
        this.playSet = playSet;
        this.playsScoreSet = playsScoreSet;
        this.score = score;
        this.challengeTitle = challengeTitle;
        this.id =id;
    }
}