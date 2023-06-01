package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.dto.BoatsInRaceDTO;
import com.example.eksamenbackendbenjamin.dto.RaceParticipationDTOShowRacesForOneBoat;
import com.example.eksamenbackendbenjamin.model.RaceParticipation;
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

    public List<BoatsInRaceDTO> findAllBoatsInRace(int raceId) {
        List<RaceParticipation> list = raceParticipationRepository.findRaceParticipationBySailRaceId(raceId);
        List<BoatsInRaceDTO> listOfDTO = new ArrayList<>();

        for(RaceParticipation raceParticipation : list){
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
}
