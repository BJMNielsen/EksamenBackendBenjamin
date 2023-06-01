package com.example.eksamenbackendbenjamin.controller;

import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTO;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.service.SailBoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SailBoatController {

    @Autowired
    SailBoatService sailBoatService;

    // SKAF ALLE SEJLBÅDE \\
    @GetMapping("/sailboats")
    public List<SailBoat> getAllSailboats(){
        return sailBoatService.getAllSailBoats();
    }

    // SKAF ÉN SEJLBÅD VIA ID \\
    @GetMapping("/sailboat/{id}")
    public SailBoat getSailBoat(@PathVariable int id) {
        return sailBoatService.getSailBoatById(id);
    }

    // CREATE ÉN SEJLBÅD \\
    @PostMapping("/sailboat")
    public ResponseEntity<SailBoat> addSailBoat(@RequestBody SailBoat sailBoat) {
        return sailBoatService.addSailBoat(sailBoat);
    }

    // UPDATE ÉN SEJLBÅD \\
    @PutMapping("/sailboat")
    public ResponseEntity<SailBoat> updateSailBoat(@RequestBody SailBoat sailBoat) {
        return sailBoatService.updateSailBoat(sailBoat);
    }

    // SLET ÉN SEJLBÅD \\
    @DeleteMapping("/sailboat/{id}")
    public ResponseEntity<SailBoat> deleteSailBoat(@PathVariable int id) {
        return sailBoatService.deleteSailBoat(id);
    }

}
