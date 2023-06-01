package com.example.eksamenbackendbenjamin.repository;

import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTO;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.model.SailRace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceParticipationRepository extends JpaRepository<RaceParticipation, Integer> {

    List<RaceParticipation> findRaceParticipationBySailBoatId(int id);
}
