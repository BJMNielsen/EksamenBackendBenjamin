package com.example.eksamenbackendbenjamin.controller;


import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.service.RaceParticipationService;
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

    @GetMapping("/raceparticipations/boat/{boatid}")
    public List<RaceParticipation> getRaceparticipationByBoatId(@PathVariable int boatid) {
        return raceParticipationService.getRaceparticipationByBoatId(boatid);
    }
// raceparticipations/race/
    @GetMapping("/raceparticipations/race/{raceId}")
    public List<RaceParticipation> getAllRaceparticipationsByRaceId(@PathVariable int raceId) {
        return raceParticipationService.getAllRaceparticipationsByRaceId(raceId);
    }

    @PostMapping("/raceparticipation")
    public ResponseEntity<RaceParticipation> addRaceParticipation(@RequestBody RaceParticipation raceParticipation) {
        return raceParticipationService.addRaceParticipation(raceParticipation);
    }

    @PutMapping("/raceparticipation")
    public ResponseEntity<RaceParticipation> updateRaceParticipation(@RequestBody RaceParticipation raceParticipation) {
        return raceParticipationService.updateRaceParticipation(raceParticipation);
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
