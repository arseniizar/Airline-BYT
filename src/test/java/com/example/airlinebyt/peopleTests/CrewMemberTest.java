package com.example.airlinebyt.peopleTests;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.CrewMember;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CrewMemberTest {

    @Test
    void testCrewFields() {
        CrewMember c = new CrewMember(
                "Crew",
                "XYZ",
                LocalDate.now().minusYears(18),
                LocalDate.now().minusYears(1),
                "Bachelor's Degree",
                7000.0,
                "Steward"
        );

        assertEquals("Crew", c.getFirstName());
        assertEquals("XYZ", c.getLastName());
        assertEquals(7000.0, c.getBaseSalary());
        assertEquals("Steward", c.getPosition());
    }

    @Test
    void testFlightsSet() {
        CrewMember c = new CrewMember(
                "Crew",
                "XYZ",
                LocalDate.now().minusYears(18),
                LocalDate.now().minusYears(1),
                "Bachelor's Degree",
                7000.0,
                "Steward"
        );

        Flight f1 = new Flight();
        Flight f2 = new Flight();

        c.assignToFlight(f1);
        c.assignToFlight(f2);

        assertEquals(2, c.getFlights().size());
        assertTrue(c.getFlights().contains(f1));
        assertTrue(c.getFlights().contains(f2));
    }
}

