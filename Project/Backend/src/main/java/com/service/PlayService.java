package com.service;

import com.model.Challenge;
import com.model.Play;
import com.model.User;
import com.repository.PlayRepository;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

@Service
public class PlayService {

    private final PlayRepository playRepository;

    public PlayService(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    public Play createPlayEntry(User user, Challenge challenge) {
        Play newPlay = new Play(user,challenge);
   
        return playRepository.save(newPlay);
    }

public List<TopScoreDTO> getTop10Scores() {
        List<Play> topPlays = playRepository.findAllByOrderByPlayScoreDesc().stream()
                                  .limit(10)
                                  .collect(Collectors.toList());

        // Convert Play entities to TopScoreDTO
        List<TopScoreDTO> topScores = topPlays.stream()
                                           .map(play -> new TopScoreDTO(
                                               play.getPlay_id(),
                                               play.getPlayScore(),
                                               play.getUserfk().getAlias(),
                                               play.getChallengefk().getChallangeTitle()
                                           ))
                                           .collect(Collectors.toList());

        return topScores;
    }
    public List<PlayerScoreDTO> getPlayerScores(int userId) {
        List<Play> playerPlays = playRepository.findByUserfkIdOrderByPlayScoreDesc(userId);

        // Convert Play entities to PlayerScoreDTO
        List<PlayerScoreDTO> playerScores = playerPlays.stream()
                .map(play -> new PlayerScoreDTO(
                        play.getPlay_id(),
                        play.getPlaySet(),
                        play.getPlaysScoreSet(),
                        play.getPlayScore(),
                        play.getChallengefk().getChallangeTitle()
                ))
                .collect(Collectors.toList());

        return playerScores;
    }
}
