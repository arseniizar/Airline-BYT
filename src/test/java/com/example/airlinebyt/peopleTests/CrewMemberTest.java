package com.example.airlinebyt.peopleTests;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.CrewMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrewMemberTest {

    @Test
    void testCrewFields() {
        CrewMember c = new CrewMember();

        c.setFirstName("Crew");
        c.setLastName("XYZ");
        c.setBaseSalary(7000.0);
        c.setPosition("Flight Attendant");

        assertEquals("Crew", c.getFirstName());
        assertEquals("XYZ", c.getLastName());
        assertEquals(7000.0, c.getBaseSalary());
        assertEquals("Flight Attendant", c.getPosition());
    }

    @Test
    void testFlightsSet() {
        CrewMember c = new CrewMember();

        Flight f1 = new Flight();
        Flight f2 = new Flight();

        c.getFlights().add(f1);
        c.getFlights().add(f2);

        assertEquals(2, c.getFlights().size());
        assertTrue(c.getFlights().contains(f1));
        assertTrue(c.getFlights().contains(f2));
    }
}

