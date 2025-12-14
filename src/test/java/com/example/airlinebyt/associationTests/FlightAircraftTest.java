package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightAircraftTest {

    @Test
    void shouldAssignAircraftToFlight() {
        Flight flight = new Flight();
        FixedWingAircraft aircraft =
                new FixedWingAircraft("Boeing 787", 250, 60.0, 10, null);

        flight.setAircraft(aircraft);

        assertEquals(aircraft, flight.getAircraft());
    }
}
