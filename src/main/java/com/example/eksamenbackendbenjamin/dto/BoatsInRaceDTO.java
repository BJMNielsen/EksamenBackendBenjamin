package com.example.eksamenbackendbenjamin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoatsInRaceDTO {

    private int sailRaceId;

    private String sailRaceName;

    private LocalDate sailRaceDate;

    private int sailBoatId;

    private String sailBoatName;

    private String boatType;

    private int points;

}
