package com.example.eksamenbackendbenjamin.service;


import com.example.eksamenbackendbenjamin.exception.ResourceAlreadyExistsException;
import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.repository.RaceParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceParticipationService {

    @Autowired
    RaceParticipationRepository raceParticipationRepository;

    public List<RaceParticipation> getAllRaceParticipations() {
        return raceParticipationRepository.findAll();
    }

    public RaceParticipation getRaceParticipationById(int id) {
        return raceParticipationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find any raceparticipation with id: " + id));
    }


    public List<RaceParticipation> getRaceparticipationByBoatId(int boatId) {
        return raceParticipationRepository.findRaceParticipationBySailBoatId(boatId);
    }

    public List<RaceParticipation> getAllRaceparticipationsByRaceId(int raceId) {
        return raceParticipationRepository.findRaceParticipationBySailRaceId(raceId);
    }

    public ResponseEntity<RaceParticipation> addRaceParticipation(RaceParticipation raceParticipation) {
        // Først tjekker vi om den allerede eksistere, så vi ikke overrider den hvis den eksistere.

        boolean exists = raceParticipationRepository.existsById(raceParticipation.getId());
        if (exists) {
            // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Raceparticipation with id: " + raceParticipation.getId() + " already exists and therefore can't be added.");
        }

        RaceParticipation newRaceParticipationSaved = raceParticipationRepository.save(raceParticipation);
        return new ResponseEntity<>(newRaceParticipationSaved, HttpStatus.OK);

    }

    public ResponseEntity<RaceParticipation> updateRaceParticipation(RaceParticipation raceParticipation) {

        RaceParticipation oldRaceParticipation = raceParticipationRepository.findById(raceParticipation.getId()).orElseThrow(()-> new ResourceNotFoundException("Participant with id: " + raceParticipation.getId() + " does not exist and therefore can't be updated"));

        raceParticipation.setSailBoat(oldRaceParticipation.getSailBoat());
        raceParticipation.setSailRace(oldRaceParticipation.getSailRace());

        if (raceParticipation.getPoints() < 0){
            raceParticipation.setPoints(oldRaceParticipation.getPoints());
        }

        RaceParticipation savedRaceParticipation = raceParticipationRepository.save(raceParticipation);
        return new ResponseEntity<>(savedRaceParticipation, HttpStatus.OK);
    }

    public ResponseEntity<RaceParticipation> deleteRaceParticipation(int id) {
        RaceParticipation deletedRaceParticipation = getRaceParticipationById(id);
        raceParticipationRepository.deleteById(id);
        return new ResponseEntity<>(deletedRaceParticipation, HttpStatus.OK);
    }

    public ResponseEntity<RaceParticipation> deleteRaceParticipationBySailBoatIdAndSailRaceId(int boatid, int raceid) {
        RaceParticipation deletedRaceParticipation = getRaceParticipationById(boatid);
        raceParticipationRepository.deleteRaceParticipationBySailBoatIdAndSailRaceId(boatid, raceid);
        return new ResponseEntity<>(deletedRaceParticipation, HttpStatus.OK);
    }
}
