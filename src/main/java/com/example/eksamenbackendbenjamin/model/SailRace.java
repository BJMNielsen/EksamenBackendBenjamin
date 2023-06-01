package com.example.eksamenbackendbenjamin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SailRace {

    public SailRace(String name, LocalDate dato){
        this.name = name;
        this.dato = dato;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sail_race_id")
    private int id;

    private String name;

    private LocalDate dato;

    // One Race to many RaceParticipations
    @OneToMany(mappedBy = "sailRace", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<RaceParticipation> raceListSailRace;

}
