package com.example.eksamenbackendbenjamin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RaceParticipationDTOShowRacesForOneBoat {

    private int boatId;

    private String boatName;

    private int raceId;

    private String raceName;

    private int points;


}
