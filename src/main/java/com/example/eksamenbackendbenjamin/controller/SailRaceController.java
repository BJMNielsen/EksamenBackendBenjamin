package com.example.eksamenbackendbenjamin.controller;

import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.service.SailBoatService;
import com.example.eksamenbackendbenjamin.service.SailRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
