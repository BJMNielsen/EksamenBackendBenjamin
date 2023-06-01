package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.repository.SailRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailRaceService {

    @Autowired
    SailRaceRepository sailRaceRepository;

    public List<SailRace> getAllSailRaces() {
        return sailRaceRepository.findAll();
    }

    public SailRace getSailRaceById(int id) {
        return sailRaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find any SailBoat with id: " + id));
    }

    public ResponseEntity<SailRace> deleteSailRace(int id) {
        // Først tjekker vi om sailRace allerede eksistere, for vi kan jo ikke slette noget der ikke eksitere.
        boolean exists = sailRaceRepository.existsById(id);
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("SailRace with id: " + id + " does not exist and therefore can't be deleted");
        }
        SailRace deletedSailRace = getSailRaceById(id);
        sailRaceRepository.deleteById(id);
        return new ResponseEntity<>(deletedSailRace, HttpStatus.OK); // Lav en log i console i frontenden der siger noget med ("Du har slettet denne bruger...)
    }


}

