package com.example.airlinebyt.operationsTests;



import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.CrewMember;
import com.example.airlinebyt.models.person.Pilot;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testBasicFields() {
        Flight flight = new Flight();

        String flightNumber = "LO1234";
        LocalDateTime dep = LocalDateTime.now().plusDays(1);
        LocalDateTime arr = dep.plusHours(2);

        flight.setFlightNumber(flightNumber);
        flight.setStatus(FlightStatus.SCHEDULED);
        flight.setExpectedDepartureTime(dep);
        flight.setExpectedArrivalTime(arr);

        assertEquals(flightNumber, flight.getFlightNumber());
        assertEquals(FlightStatus.SCHEDULED, flight.getStatus());
        assertEquals(dep, flight.getExpectedDepartureTime());
        assertEquals(arr, flight.getExpectedArrivalTime());
    }

    @Test
    void testAssignAircraft() {
        Flight flight = new Flight();
        Commercial aircraft = new Commercial();

        aircraft.setModel("Boeing 737");
        aircraft.setCapacity(180);

        flight.setAircraft(aircraft);

        assertNotNull(flight.getAircraft());
        assertEquals(aircraft, flight.getAircraft());
        assertEquals("Boeing 737", flight.getAircraft().getModel());
        assertEquals(180, flight.getAircraft().getCapacity());
    }

    @Test
    void testAssignAirports() {
        Flight flight = new Flight();

        Airport origin = new Airport(
                "WAW",
                "Warsaw Chopin Airport",
                new Location("Poland", "Warsaw")
        );
        Airport dest = new Airport(
                "BER",
                "Berlin Brandenburg Airport",
                new Location("Germany", "Berlin")
        );


        flight.setOrigin(origin);
        flight.setDestination(dest);

        assertEquals(origin, flight.getOrigin());
        assertEquals(dest, flight.getDestination());
    }

    @Test
    void testPilots() {
        Flight flight = new Flight();

        Pilot p1 = new Pilot(
                "Pilot1",
                "ABC",
                LocalDateTime.now().minusYears(35).toLocalDate(),
                LocalDateTime.now().minusYears(5).toLocalDate(),
                "Master's Degree",
                "LIC123",
                LocalDateTime.now().plusYears(5).toLocalDate(),
                12000.0
        );
        Pilot p2 = new Pilot(
                "Pilot2",
                "DEF",
                LocalDateTime.now().minusYears(40).toLocalDate(),
                LocalDateTime.now().minusYears(10).toLocalDate(),
                "Master's Degree",
                "LIC456",
                LocalDateTime.now().plusYears(3).toLocalDate(),
                13000.0
        );

        flight.getPilots().add(p1);
        flight.getPilots().add(p2);

        assertEquals(2, flight.getPilots().size());
        assertTrue(flight.getPilots().contains(p1));
        assertTrue(flight.getPilots().contains(p2));
    }

    @Test
    void testCrew() {
        Flight flight = new Flight();

        CrewMember c1 = new CrewMember(
                "Crew1",
                "GHI",
                LocalDateTime.now().minusYears(28).toLocalDate(),
                LocalDateTime.now().minusYears(3).toLocalDate(),
                "Bachelor's Degree",
                8000.0,
                "Flight Attendant"
        );
        CrewMember c2 = new CrewMember(
                "Crew2",
                "JKL",
                LocalDateTime.now().minusYears(30).toLocalDate(),
                LocalDateTime.now().minusYears(5).toLocalDate(),
                "Bachelor's Degree",
                8500.0,
                "Flight Attendant"
        );

        flight.getCrew().add(c1);
        flight.getCrew().add(c2);

        assertEquals(2, flight.getCrew().size());
        assertTrue(flight.getCrew().contains(c1));
        assertTrue(flight.getCrew().contains(c2));
    }

    @Test
    void testActualTimes() {
        Flight flight = new Flight();

        LocalDateTime actualDep = LocalDateTime.now();
        LocalDateTime actualArr = actualDep.plusHours(3);

        flight.setActualDepartureTime(actualDep);
        flight.setActualArrivalTime(actualArr);

        assertEquals(actualDep, flight.getActualDepartureTime());
        assertEquals(actualArr, flight.getActualArrivalTime());
    }
}

