package com.example.eksamenbackendbenjamin.repository;

import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RaceParticipationRepository extends JpaRepository<RaceParticipation, Integer> {

    List<RaceParticipation> findRaceParticipationBySailBoatId(int id);

    List<RaceParticipation> findRaceParticipationBySailRaceId(int id);


    ResponseEntity<RaceParticipation> deleteRaceParticipationBySailBoatIdAndSailRaceId(int boatid, int raceid);
}
