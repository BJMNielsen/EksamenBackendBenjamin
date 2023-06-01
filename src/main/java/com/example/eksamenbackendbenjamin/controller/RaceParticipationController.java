package com.example.eksamenbackendbenjamin.controller;

import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTO;
import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTOShowRacesForOneBoat;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.service.RaceParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class RaceParticipationController {

    @Autowired
    RaceParticipationService raceParticipationService;

    @GetMapping("/raceparticipation")
    public List<RaceParticipation> getAllSailboats(){
        return raceParticipationService.getAllRaceParticipations();
    }

    @GetMapping("/raceparticipation/{id}")
    public List<RaceParticipationDTOShowRacesForOneBoat> getRaceparticipationForBoat(@PathVariable int id){
        return raceParticipationService.getRaceParticipationForBoat(id);
    }
}
