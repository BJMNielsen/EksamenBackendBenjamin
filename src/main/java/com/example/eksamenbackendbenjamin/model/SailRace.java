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
@Table(name = "tbl_sail_race")
public class SailRace {

    public SailRace(String name, LocalDate sailRaceDate){
        this.name = name;
        this.sailRaceDate = sailRaceDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sail_race_id")
    private int id;

    @Column(name = "sail_race_name")
    private String name;

    private LocalDate sailRaceDate;

    // One Race to many RaceParticipations
    @OneToMany(mappedBy = "sailRace", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<RaceParticipation> raceListSailRace;

}
