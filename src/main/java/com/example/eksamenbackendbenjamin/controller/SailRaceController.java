package com.example.eksamenbackendbenjamin.controller;

import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.service.SailBoatService;
import com.example.eksamenbackendbenjamin.service.SailRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SailRaceController {

    @Autowired
    SailRaceService sailRaceService;

    // SKAF ALLE SEJLRACES \\
    @GetMapping("/sailraces")
    public List<SailRace> getAllSailRaces(){
        return sailRaceService.getAllSailRaces();
    }

    @GetMapping("/sailrace/{id}")
    public SailRace getSailRace(@PathVariable int id) {
        return sailRaceService.getSailRaceById(id);
    }

    // SLET ET RACE \\
    @DeleteMapping("/sailrace/{id}")
    public ResponseEntity<SailRace> deleteSailRace(@PathVariable int id) {
        return sailRaceService.deleteSailRace(id);
    }

}

