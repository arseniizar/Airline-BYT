package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.CrewMember;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlightCrewTest {
    //bidirectional

    @Test
    void shouldAssignCrewMemberToFlight() {
        Flight flight = new Flight();
        CrewMember crew = new CrewMember(
                "Anna", "Smith",
                LocalDate.of(1995, 5, 5),
                LocalDate.of(2020, 1, 1),
                "Aviation", 3000.0, "Steward"
        );

        flight.addCrewMember(crew);

        assertTrue(flight.getCrew().contains(crew));
        assertTrue(crew.getFlights().contains(flight));
    }

    @Test
    void shouldRemoveCrewMemberFromFlight() {
        Flight flight = new Flight();
        CrewMember crew = new CrewMember(
                "Anna", "Smith",
                LocalDate.of(1995, 5, 5),
                LocalDate.of(2020, 1, 1),
                "Aviation", 3000.0, "Steward"
        );

        flight.addCrewMember(crew);
        flight.removeCrewMember(crew);

        assertFalse(flight.getCrew().contains(crew));
        assertFalse(crew.getFlights().contains(flight));
    }
}

