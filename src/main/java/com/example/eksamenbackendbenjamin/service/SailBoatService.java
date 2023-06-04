package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.exception.ResourceAlreadyExistsException;
import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.repository.RaceParticipationRepository;
import com.example.eksamenbackendbenjamin.repository.SailBoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailBoatService {

    @Autowired
    SailBoatRepository sailBoatRepository;

    @Autowired
    RaceParticipationRepository raceParticipationRepository;

    public List<SailBoat> getAllSailBoats() {
        return sailBoatRepository.findAll();
    }

    public SailBoat getSailBoatById(int id) {
        return sailBoatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find any SailBoat with id: " + id));
    }

    // CREATE
    public ResponseEntity<SailBoat> addSailBoat(SailBoat sailBoat) {
        // Først tjekker vi om sailBoat allerede eksistere, så vi ikke overrider den hvis den eksistere.
        //raceParticipationDTO.checkForMistakes();
        //SailBoat newSailBoat = raceParticipationDTO.getSailBoat();
        SailBoat newSailBoat = sailBoat;
       /// List<SailRace> raceList = raceParticipationDTO.getListOfSailRaces();

        boolean exists = sailBoatRepository.existsById(newSailBoat.getId());
        if (exists) {
            // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("SailBoat with id: " + newSailBoat.getId() + " already exists and therefore can't be added.");
        }
        SailBoat newSailBoatSaved = sailBoatRepository.save(newSailBoat);
        //addRaceParticipationToSailBoat(raceList, newSailBoatSaved);
        return new ResponseEntity<>(newSailBoatSaved, HttpStatus.OK);
    }
/*
    private void addRaceParticipationToSailBoat(List<SailRace> raceList, SailBoat sailBoat) {
    List<RaceParticipation> listOfSavedRaceParticipation = new ArrayList<>();

        for (SailRace race : raceList) {
            RaceParticipation raceParticipation = new RaceParticipation();
            raceParticipation.setSailBoat(sailBoat);
            raceParticipation.setSailRace(race);

            RaceParticipation raceParticipationSaved = raceParticipationRepository.save(raceParticipation);
            listOfSavedRaceParticipation.add(raceParticipationSaved);

        }
    }
*/

    public ResponseEntity<SailBoat> updateSailBoat(SailBoat sailBoat) {
        // Først tjekker vi om Sailboat allerede eksistere, så vi ikke overrider den hvis den eksistere.
        boolean exists = sailBoatRepository.existsById(sailBoat.getId());
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("Sailboat with id: " + sailBoat.getId() + " does not exist and therefore can't be updated");
        }

        if(sailBoat.getName() == null){
            SailBoat oldSailBoat = getSailBoatById(sailBoat.getId());
            sailBoat.setBoatType(oldSailBoat.getBoatType());
            sailBoat.setName(oldSailBoat.getName());
        }

        // Hvis Sailboat eksistere, tager vi den nye info fra vores requestbody og overwriter vores sailboat nu med den nye info, dvs vi saver oveni en allerede eksisterende sailboat, bare med ny info.
        SailBoat newSailBoat = sailBoatRepository.save(sailBoat);
        return new ResponseEntity<>(newSailBoat, HttpStatus.OK);
    }

    public ResponseEntity<SailBoat> deleteSailBoat(int id) {
        // Først tjekker vi om sailBoat allerede eksistere, for vi kan jo ikke slette noget der ikke eksitere.
        boolean exists = sailBoatRepository.existsById(id);
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("SailBoat with id: " + id + " does not exist and therefore can't be deleted");
        }
        SailBoat deletedSailBoat = getSailBoatById(id);
        sailBoatRepository.deleteById(id);
        return new ResponseEntity<>(deletedSailBoat, HttpStatus.OK); // Lav en log i console i frontenden der siger noget med ("Du har slettet denne bruger...)
    }
}

