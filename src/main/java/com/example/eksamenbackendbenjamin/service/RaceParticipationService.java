package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.dto.BoatsInRaceDTO;
import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTOShowRacesForOneBoat;
import com.example.eksamenbackendbenjamin.exception.ResourceAlreadyExistsException;
import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.repository.RaceParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<RaceParticipationDTOShowRacesForOneBoat> getRaceParticipationForBoat(int id) {

        List<RaceParticipation> list = raceParticipationRepository.findRaceParticipationBySailBoatId(id);
        List<RaceParticipationDTOShowRacesForOneBoat> listOfDTO = new ArrayList<>();

        for (RaceParticipation raceParticipation : list) {
            RaceParticipationDTOShowRacesForOneBoat dto = new RaceParticipationDTOShowRacesForOneBoat();

            dto.setRaceParticipationId(raceParticipation.getId());

            dto.setBoatId(raceParticipation.getSailBoat().getId());
            dto.setBoatName(raceParticipation.getSailBoat().getName());

            dto.setRaceId(raceParticipation.getId());
            dto.setRaceName(raceParticipation.getSailRace().getName());

            dto.setPoints(raceParticipation.getPoints());

            listOfDTO.add(dto);
        }
        return listOfDTO;
    }

    public List<BoatsInRaceDTO> findAllBoatsInRace(int raceId) {
        List<RaceParticipation> list = raceParticipationRepository.findRaceParticipationBySailRaceId(raceId);
        List<BoatsInRaceDTO> listOfDTO = new ArrayList<>();

        for (RaceParticipation raceParticipation : list) {
            BoatsInRaceDTO boatsInRaceDTO = new BoatsInRaceDTO();

            boatsInRaceDTO.setSailRaceId(raceParticipation.getSailRace().getId());
            boatsInRaceDTO.setSailRaceName(raceParticipation.getSailRace().getName());
            boatsInRaceDTO.setSailRaceDate(raceParticipation.getSailRace().getSailRaceDate());

            boatsInRaceDTO.setSailBoatId(raceParticipation.getSailBoat().getId());
            boatsInRaceDTO.setSailBoatName(raceParticipation.getSailBoat().getName());
            boatsInRaceDTO.setBoatType(raceParticipation.getSailBoat().getBoatType());
            boatsInRaceDTO.setPoints(raceParticipation.getSailBoat().getPoints());

            listOfDTO.add(boatsInRaceDTO);
        }
        return listOfDTO;
    }

    public ResponseEntity<RaceParticipation> addRaceParticipation(RaceParticipation raceParticipation) {
        // Først tjekker vi om den allerede eksistere, så vi ikke overrider den hvis den eksistere.
        RaceParticipation newRaceParticipation = raceParticipation;

        boolean exists = raceParticipationRepository.existsById(newRaceParticipation.getId());
        if (exists) {
            // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Racepartisipation with id: " + raceParticipation.getId() + " already exists and therefore can't be added.");
        }

        RaceParticipation newRaceParticipationSaved = raceParticipationRepository.save(newRaceParticipation);
        return new ResponseEntity<>(newRaceParticipationSaved, HttpStatus.OK);

    }

    public ResponseEntity<RaceParticipation> deleteRaceParticipation(int id) {
        RaceParticipation deletedRaceParticipation = getRaceParticipationById(id);
        raceParticipationRepository.deleteById(id);
        return new ResponseEntity<>(deletedRaceParticipation, HttpStatus.OK);
    }

    public ResponseEntity<RaceParticipation> deleteRaceParticipationBySailBoatIdAndSailRaceId(int boatid, int raceid){
        RaceParticipation deletedRaceParticipation = getRaceParticipationById(boatid);
        raceParticipationRepository.deleteRaceParticipationBySailBoatIdAndSailRaceId(boatid, raceid);
        return new ResponseEntity<>(deletedRaceParticipation, HttpStatus.OK);
    }
}
