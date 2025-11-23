package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Pilot;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PilotTest {

    @Test
    void testPilotFields() {
        Pilot pilot = new Pilot();

        pilot.setFirstName("Pilot");
        pilot.setLastName("XYZ");
        pilot.setLicenceNumber("123");
        pilot.setLicenceWarranty(LocalDate.of(2035, 1, 1));
        pilot.setBaseSalary(10000.0);

        assertEquals("Pilot", pilot.getFirstName());
        assertEquals("XYZ", pilot.getLastName());
        assertEquals("123", pilot.getLicenceNumber());
        assertEquals(LocalDate.of(2035, 1, 1), pilot.getLicenceWarranty());
        assertEquals(10000.0, pilot.getBaseSalary());
    }

    @Test
    void testFlightsSet() {
        Pilot pilot = new Pilot();

        Flight f1 = new Flight();
        Flight f2 = new Flight();

        pilot.getFlights().add(f1);
        pilot.getFlights().add(f2);

        assertEquals(2, pilot.getFlights().size());
        assertTrue(pilot.getFlights().contains(f1));
        assertTrue(pilot.getFlights().contains(f2));
    }
}

