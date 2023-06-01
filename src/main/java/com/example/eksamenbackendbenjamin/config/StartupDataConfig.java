package com.example.eksamenbackendbenjamin.config;

import com.example.eksamenbackendbenjamin.model.RaceParticipation;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.model.SailRace;
import com.example.eksamenbackendbenjamin.repository.RaceParticipationRepository;
import com.example.eksamenbackendbenjamin.repository.SailBoatRepository;
import com.example.eksamenbackendbenjamin.repository.SailRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StartupDataConfig implements ApplicationRunner{

    @Autowired
    SailBoatRepository sailBoatRepository;

    @Autowired
    SailRaceRepository sailRaceRepository;

    @Autowired
    RaceParticipationRepository raceParticipationRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Starter program op");

        createRaces();
        List<SailBoat> listOfBoats = createSailBoats();

        for (SailBoat sailBoat: listOfBoats){
            System.out.println(sailBoat.getId() + ": " + sailBoat.getName());
            createRaceParticipation(sailBoat);
        }
    }

    public void createRaceParticipation(SailBoat sailBoat){
        List<SailRace> allRaces = sailRaceRepository.findAll();
        boolean unEqualNumber = sailBoat.getId() % 2 != 0;

        if(unEqualNumber){
            SailRace ultimateRace = allRaces.get(0);

            RaceParticipation raceParticipationUltimateRace = new RaceParticipation();
            raceParticipationUltimateRace.setSailBoat(sailBoat);
            raceParticipationUltimateRace.setSailRace(ultimateRace);
            raceParticipationUltimateRace.setPoints(11 + sailBoat.getId());
            raceParticipationRepository.save(raceParticipationUltimateRace);

            SailRace eksamensKonkurrence = allRaces.get(2);

            RaceParticipation raceParticipationEksamen = new RaceParticipation();
            raceParticipationEksamen.setSailBoat(sailBoat);
            raceParticipationEksamen.setSailRace(eksamensKonkurrence);
            raceParticipationEksamen.setPoints(9 + sailBoat.getId());
            raceParticipationRepository.save(raceParticipationEksamen);

        } else {
            SailRace børgesSejlkonkurrence = allRaces.get(1);
            RaceParticipation raceParticipationBørges = new RaceParticipation();
            raceParticipationBørges.setSailBoat(sailBoat);
            raceParticipationBørges.setSailRace(børgesSejlkonkurrence);
            raceParticipationBørges.setPoints(14 + sailBoat.getId());
            raceParticipationRepository.save(raceParticipationBørges);
        }
    }

    public List<SailBoat> createSailBoats(){

        List<SailBoat> listOfBoats = new ArrayList<>();

        SailBoat sailBoat1 = new SailBoat("Queen Mary", "40fod");
        sailBoatRepository.save(sailBoat1);

        SailBoat sailBoat2 = new SailBoat("Big Splash", "25fod");
        sailBoatRepository.save(sailBoat2);

        SailBoat sailBoat3 = new SailBoat("Merete's søstjerne", "25-40fod");
        sailBoatRepository.save(sailBoat3);

        listOfBoats.add(sailBoat1);
        listOfBoats.add(sailBoat2);
        listOfBoats.add(sailBoat3);

        return listOfBoats;
    }

    public void createRaces(){

        SailRace ultimateRace = new SailRace();
        ultimateRace.setName("Ultimate Race");
        ultimateRace.setDato(LocalDate.now());
        sailRaceRepository.save(ultimateRace);

        SailRace børgesSejlKonkurrence = new SailRace();
        børgesSejlKonkurrence.setName("Børges Sejlkonkurrence");
        børgesSejlKonkurrence.setDato(LocalDate.now());
        sailRaceRepository.save(børgesSejlKonkurrence);

        SailRace eksamensSejllads = new SailRace();
        eksamensSejllads.setName("Eksamens Store Sejlladskonkurrence");
        eksamensSejllads.setDato(LocalDate.now());
        sailRaceRepository.save(eksamensSejllads);
    }
}
