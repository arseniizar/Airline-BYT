package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightAirportTest {

    @Test
    void shouldSetDestinationAirport() {
        Flight flight = new Flight();
        Airport destination = new Airport(
                "JFK", "John F. Kennedy Airport",
                new Location("USA", "New York")
        );

        flight.setDestination(destination);

        assertEquals(destination, flight.getDestination());
    }
}

