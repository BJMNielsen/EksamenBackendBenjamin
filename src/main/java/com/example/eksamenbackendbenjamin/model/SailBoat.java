package com.example.eksamenbackendbenjamin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SailBoat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sail_boat_id")
    private int id;

    private String name;

    private String boatType;

    private int points;

    // One SailBoat to many RaceParticipations
    // Vi mapper til vores variabel sailBoat inde i RaceParticipation
    @OneToMany(mappedBy = "sailBoat", cascade = CascadeType.REMOVE) // Hvis en sailBoat bliver deleted, så bliver alle RaceParticipation der hører til sailBoaten også slettet
    @JsonBackReference // for at undgå infinity loop
    private List<RaceParticipation> sailBoatRaceList;

    public SailBoat(String name, String boatType){
        this.name = name;
        this.boatType = boatType;
    }

}
