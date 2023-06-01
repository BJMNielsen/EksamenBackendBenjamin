package com.example.eksamenbackendbenjamin.service;

import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.repository.SailRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailRaceService {

    @Autowired
    SailRaceRepository sailRaceRepository;

    public List<SailRace> getAllSailRaces() {
        return sailRaceRepository.findAll();
    }
}
