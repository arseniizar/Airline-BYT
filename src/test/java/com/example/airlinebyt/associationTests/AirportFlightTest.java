package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportFlightTest {
    //qualified association

    @Test
    void shouldAddFlightAsDeparture() {
        Airport airport = new Airport("WAW", "Chopin Airport",
                new Location("Poland", "Warsaw"));

        Flight flight = new Flight();
        flight.setFlightNumber("LO123");

        airport.addDeparture(flight);

        assertTrue(airport.findDepartingFlightByNumber("LO123").isPresent());
        assertEquals(airport, flight.getOrigin());
    }

    @Test
    void shouldRemoveDeparture() {
        Airport airport = new Airport("WAW", "Chopin Airport",
                new Location("Poland", "Warsaw"));

        Flight flight = new Flight();
        flight.setFlightNumber("LO123");

        airport.addDeparture(flight);
        airport.removeDeparture(flight);

        assertTrue(airport.getDepartingFlights().isEmpty());
    }
}
