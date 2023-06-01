package com.example.eksamenbackendbenjamin.dto;

import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.model.SailRace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RaceParticipationDTO {

    private SailBoat sailBoat;

    private List<SailRace> listOfSailRaces;

    private int points;

    public void checkForMistakes() {
        if (sailBoat == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a SailBoat");
        }
        if (listOfSailRaces == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a list of Races");
        }
    }
}
