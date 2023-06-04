package com.example.eksamenbackendbenjamin.service;


import com.example.eksamenbackendbenjamin.exception.ResourceNotFoundException;
import com.example.eksamenbackendbenjamin.model.SailBoat;
import com.example.eksamenbackendbenjamin.repository.SailBoatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;


class SailBoatServiceTest {

    @Mock
    private SailBoatRepository repository;

    @InjectMocks
    private SailBoatService service;

    private List<SailBoat> mockedSailBoatDB = new ArrayList<>();

    @BeforeEach
    void setUp() {

        // Vi mocker lige vores database af sailboats her

        SailBoat sailBoat = new SailBoat(1, "Joleen", "25fod");
        SailBoat sailBoat2 = new SailBoat(2, "AnkerJohnny", "37fod");
        SailBoat sailBoat3 = new SailBoat(3, "Titanic", "40fod");

        mockedSailBoatDB = Arrays.asList(sailBoat, sailBoat2, sailBoat3);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSailBoatById() {

            // Mocker opførselen af vores repository
            int correctSailBoatId = 1;
            int incorrectSailBoatId = 100;
            mockingFindById(correctSailBoatId); // Når nogen bruger den her metode, så skal du give en optional med en sejlbåd med samme id tilbage.
            mockingFindById(incorrectSailBoatId); // Når nogen bruger den her metode, så skal du give en optional et nul tilbage.

            // Vi kalder service metoden
            Assertions.assertDoesNotThrow(()-> service.getSailBoatById(correctSailBoatId)); // Vi asserter at det korrekte IKKE vil throwe en fejl.
            SailBoat resultSailBoat = service.getSailBoatById(correctSailBoatId);

            // Vi tjekker om resultatet er korrekt med 3 små assertions.
            Assertions.assertThrows( ResourceNotFoundException.class,()-> service.getSailBoatById(incorrectSailBoatId)); // Vi asserter at der bliver throwet en ResourceNotFoundException når vi bruger et id der ikke eksistere.
            Assertions.assertEquals(correctSailBoatId, resultSailBoat.getId()); // Er vores id på 1 det samme som id'et fra vores sailboat fra repositoriet vi kaldte med id 1?
            Assertions.assertNotEquals(incorrectSailBoatId, resultSailBoat.getId()); // Samme igen bare at det IKKE er equals med vores forkert sailBoatId.
    }

    private void mockingFindById(int id) { // Mocker repository findById metoden

        SailBoat foundSailBoat = null;
        for (SailBoat sailboat : mockedSailBoatDB) {
            if (sailboat.getId() == id) {
                foundSailBoat = sailboat;
            }
        }
        Optional<SailBoat> optionalSailBoat = Optional.ofNullable(foundSailBoat);
        when(repository.findById(id)).thenReturn(optionalSailBoat); // Når service kalder repository.findById så returner vi optionalSailBoat.
    }

}