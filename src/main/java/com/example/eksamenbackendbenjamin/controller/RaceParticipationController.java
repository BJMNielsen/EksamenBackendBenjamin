package com.example.eksamenbackendbenjamin.controller;

import com.example.eksamenbackendbenjamin.dto.BoatsInRaceDTO;
import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTOShowRacesForOneBoat;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.service.RaceParticipationService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class RaceParticipationController {

    @Autowired
    RaceParticipationService raceParticipationService;

    @GetMapping("/raceparticipation")
    public List<RaceParticipation> getAllSailboats() {
        return raceParticipationService.getAllRaceParticipations();
    }

    @GetMapping("/raceparticipation/{id}")
    public List<RaceParticipationDTOShowRacesForOneBoat> getRaceparticipationForBoat(@PathVariable int id) {
        return raceParticipationService.getRaceParticipationForBoat(id);
    }

    @GetMapping("/boats/race/{raceId}")
    public List<BoatsInRaceDTO> getAllBoatsInRace(@PathVariable int raceId) {
        return raceParticipationService.findAllBoatsInRace(raceId);
    }

    @PostMapping("/raceparticipation")
    public ResponseEntity<RaceParticipation> addRaceParticipation(@RequestBody RaceParticipation raceParticipation){
        return raceParticipationService.addRaceParticipation(raceParticipation);
    }

    @DeleteMapping("/raceparticipation/{id}")
    public ResponseEntity<RaceParticipation> deleteRaceParticipation(@PathVariable int id) {
        return raceParticipationService.deleteRaceParticipation(id);
    }

    @DeleteMapping("/raceparticipation/{raceid}/{boatid}")
    public ResponseEntity<RaceParticipation> deleteRaceParticipation(@PathVariable int raceid, @PathVariable int boatid) {
        return raceParticipationService.deleteRaceParticipationBySailBoatIdAndSailRaceId(raceid, boatid);
    }




}
