package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Pilot;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlightPilotTest {
    //qualified association

    @Test
    void shouldAssignPilotWithRole() {
        Flight flight = new Flight();

        Pilot pilot = new Pilot(
                "Tom", "Captain",
                LocalDate.of(1980, 1, 1),
                LocalDate.of(2010, 1, 1),
                "PJATK",
                "123",
                LocalDate.now().plusYears(2),
                8000.0
        );

        flight.assignPilot(pilot, "CAPTAIN");

        assertTrue(flight.getPilots().contains(pilot));
        assertEquals("CAPTAIN", flight.getPilotRoles().get(pilot));
        assertTrue(pilot.getFlights().contains(flight));
    }
}

