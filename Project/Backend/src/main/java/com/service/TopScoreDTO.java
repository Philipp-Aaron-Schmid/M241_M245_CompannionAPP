package com.service;

import lombok.Data;

@Data
public class TopScoreDTO {

    private int id;
    private int score;
    private String playerAlias;
    private String challengeTitle;

    public TopScoreDTO(int id, int score, String playerAlias, String challengeTitle) {
        this.id = id;
        this.score = score;
        this.playerAlias = playerAlias;
        this.challengeTitle = challengeTitle;
    }
}