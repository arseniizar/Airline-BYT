package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Pilot;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PilotTest {

    @Test
    void testPilotFields() {
        Pilot pilot = new Pilot(
                "Pilot",
                "XYZ",
                LocalDate.now().minusYears(30),
                LocalDate.now().minusYears(3),
                "Master's Degree",
                "123",
                LocalDate.of(2035, 1, 1),
                10000.0
        );

        assertEquals("Pilot", pilot.getFirstName());
        assertEquals("XYZ", pilot.getLastName());
        assertEquals("123", pilot.getLicenceNumber());
        assertEquals(LocalDate.of(2035, 1, 1), pilot.getLicenceWarranty());
        assertEquals(10000.0, pilot.getBaseSalary());
    }

    @Test
    void testFlightsSet() {
        Pilot pilot = new Pilot(
                "Pilot",
                "XYZ",
                LocalDate.now().minusYears(30),
                LocalDate.now().minusYears(3),
                "Master's Degree",
                "123",
                LocalDate.of(2035, 1, 1),
                10000.0
        );

        Flight f1 = new Flight();
        Flight f2 = new Flight();

        pilot.addFlight(f1);
        pilot.addFlight(f2);

        assertEquals(2, pilot.getFlights().size());
        assertTrue(pilot.getFlights().contains(f1));
        assertTrue(pilot.getFlights().contains(f2));
    }
}

