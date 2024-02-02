package com.repository;

import com.model.Challenge;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    Set<Challenge> findByChallangeDisplayTrue();
}