package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTOShowRacesForOneBoat;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.repository.RaceParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<RaceParticipationDTOShowRacesForOneBoat> getRaceParticipationForBoat(int id) {

        List<RaceParticipation> list = raceParticipationRepository.findRaceParticipationBySailBoatId(id);
        List<RaceParticipationDTOShowRacesForOneBoat> listOfDTO = new ArrayList<>();

        for(RaceParticipation raceParticipation : list){
            RaceParticipationDTOShowRacesForOneBoat dto = new RaceParticipationDTOShowRacesForOneBoat();

            dto.setBoatId(raceParticipation.getSailBoat().getId());
            dto.setBoatName(raceParticipation.getSailBoat().getName());

            dto.setRaceId(raceParticipation.getId());
            dto.setRaceName(raceParticipation.getSailRace().getName());

            dto.setPoints(raceParticipation.getPoints());

            listOfDTO.add(dto);
        }
        return listOfDTO;
    }
}
