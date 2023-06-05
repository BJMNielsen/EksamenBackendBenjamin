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
import java.util.Random;

@Configuration
public class StartupDataConfig implements ApplicationRunner {

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

        //Vi laver en sejlbåd der er med i alle races
        SailBoat sailBoatForEveryRace = new SailBoat("Uma Jolie", "25fod");
        createSailBoatInEveryRace(sailBoatForEveryRace);

        for (SailBoat sailBoat : listOfBoats) {
            System.out.println(sailBoat.getId() + ": " + sailBoat.getName());
            //createRaceParticipation(sailBoat);
        }
    }

    //Skriv en metode der kan oprette en sejlbåd som deltager i alle kapsejladser.
    public void createSailBoatInEveryRace(SailBoat sailBoat) {

        SailBoat newSailBoat = sailBoatRepository.save(sailBoat);

        List<SailRace> allRaces = sailRaceRepository.findAll();

        for (SailRace race : allRaces) {
            RaceParticipation raceParticipation = new RaceParticipation();
            raceParticipation.setSailBoat(newSailBoat);
            raceParticipation.setSailRace(race);
            raceParticipationRepository.save(raceParticipation);
        }
    }

    public void createRaceParticipation(SailBoat sailBoat) {
        List<SailRace> allRaces = sailRaceRepository.findAll();
        boolean unEqualNumber = sailBoat.getId() % 2 != 0;

        if (unEqualNumber) {
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

    public List<SailBoat> createSailBoats() {

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

    public LocalDate generateRandomDate () {
        LocalDate currentDate = LocalDate.now(); // Get the current date

        // Generate a random number of days to add to the current date
        Random random = new Random();
        int randomDays = random.nextInt(365); // Generate a random number between 0 and 364

        LocalDate futureDate = currentDate.plusDays(randomDays); // Add the random number of days to the current date
        return futureDate;
    }

    public void createRaces() {

        LocalDate dateForUltimateRace = generateRandomDate();

        SailRace ultimateRace25fod = new SailRace();
        ultimateRace25fod.setName("Ultimate Race 25fod");
        ultimateRace25fod.setSailRaceDate(dateForUltimateRace);
        sailRaceRepository.save(ultimateRace25fod);

        SailRace ultimateRace2540 = new SailRace();
        ultimateRace2540.setName("Ultimate Race 25-40fod");
        ultimateRace2540.setSailRaceDate(dateForUltimateRace);
        sailRaceRepository.save(ultimateRace2540);


        SailRace ultimateRace40 = new SailRace();
        ultimateRace40.setName("Ultimate Race 40fod");
        ultimateRace40.setSailRaceDate(dateForUltimateRace);
        sailRaceRepository.save(ultimateRace40);

        LocalDate dateBørge = generateRandomDate();

        SailRace børgesSejlKonkurrence25 = new SailRace();
        børgesSejlKonkurrence25.setName("Børges Sejlkonkurrence 25fod");
        børgesSejlKonkurrence25.setSailRaceDate(dateBørge);
        sailRaceRepository.save(børgesSejlKonkurrence25);

        SailRace børgesSejlKonkurrence2540 = new SailRace();
        børgesSejlKonkurrence2540.setName("Børges Sejlkonkurrence 25-40fod");
        børgesSejlKonkurrence2540.setSailRaceDate(dateBørge);
        sailRaceRepository.save(børgesSejlKonkurrence2540);

        SailRace børgesSejlKonkurrence40 = new SailRace();
        børgesSejlKonkurrence40.setName("Børges Sejlkonkurrence 40fod");
        børgesSejlKonkurrence40.setSailRaceDate(dateBørge);
        sailRaceRepository.save(børgesSejlKonkurrence40);


        LocalDate dateEksamensSejlads = generateRandomDate();
        SailRace eksamensSejllads25 = new SailRace();
        eksamensSejllads25.setName("Eksamens Store Sejlladskonkurrence 25fod");
        eksamensSejllads25.setSailRaceDate(dateEksamensSejlads);
        sailRaceRepository.save(eksamensSejllads25);

        SailRace eksamensSejllads2540 = new SailRace();
        eksamensSejllads2540.setName("Eksamens Store Sejlladskonkurrence 25-40fod");
        eksamensSejllads2540.setSailRaceDate(dateEksamensSejlads);
        sailRaceRepository.save(eksamensSejllads2540);

        SailRace eksamensSejllads40 = new SailRace();
        eksamensSejllads40.setName("Eksamens Store Sejlladskonkurrence 40fod");
        eksamensSejllads40.setSailRaceDate(dateEksamensSejlads);
        sailRaceRepository.save(eksamensSejllads40);
    }
}
