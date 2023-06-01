package com.example.eksamenbackendbenjamin.model;

import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RaceParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // Many Races to one sailboat
    @JoinColumn(name = "sail_boat_id", referencedColumnName = "sail_boat_id")
    private SailBoat sailBoat;

    @ManyToOne // Many Races to one sailRace
    @JoinColumn(name = "sail_race_id", referencedColumnName = "sail_race_id")
    private SailRace sailRace;

    private int points;


}
