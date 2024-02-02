package com.repository;

import com.model.Challenge;
import com.model.Play;
import com.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, Integer> {

    List<Play> findByUserfkAndChallengefk(User userfk, Challenge challengeFk);

    
    List<Play> findAllByOrderByPlayScoreDesc();


    List<Play> findByUserfkIdOrderByPlayScoreDesc(int userId);

    void deleteByChallengefk(Challenge challenge);

    void deleteByUserfk(User user);
}