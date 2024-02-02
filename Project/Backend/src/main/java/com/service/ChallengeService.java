package com.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Challenge;
import com.repository.ChallengeRepository;

@Service
public class ChallengeService {

    private static ChallengeRepository challengeRepository;


    public ChallengeService(ChallengeRepository challengeRepository) {
        ChallengeService.challengeRepository = challengeRepository;


    }

    public static void saveChallange(String challangeString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Challenge challenge = objectMapper.readValue(challangeString, Challenge.class);
            challengeRepository.save(challenge);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

}
