package com.example.airlinebyt.operationsTests;



import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    static class TestAircraft extends Aircraft {
        public TestAircraft() {
            super("A320", 180, new CommercialRole(700));
        }

        @Override
        public String getType() {
            return "";
        }
    }

    @Test
    void shouldSetValidFlightNumber() {
        Flight flight = new Flight();

        flight.setFlightNumber("LO123");

        assertEquals("LO123", flight.getFlightNumber());
    }

    @Test
    void shouldRejectInvalidFlightNumber() {
        Flight flight = new Flight();

        assertThrows(IllegalArgumentException.class, () -> flight.setFlightNumber(null));
        assertThrows(IllegalArgumentException.class, () -> flight.setFlightNumber(""));
    }

    @Test
    void shouldSetValidExpectedDepartureTime() {
        Flight flight = new Flight();
        LocalDateTime future = LocalDateTime.now().plusHours(2);

        flight.setExpectedDepartureTime(future);

        assertEquals(future, flight.getExpectedDepartureTime());
    }

    @Test
    void shouldRejectPastExpectedDepartureTime() {
        Flight flight = new Flight();
        LocalDateTime past = LocalDateTime.now().minusHours(1);

        assertThrows(IllegalArgumentException.class, () ->
                flight.setExpectedDepartureTime(past));
    }

    @Test
    void shouldSetValidArrivalTimeAfterDeparture() {
        Flight flight = new Flight();
        LocalDateTime departure = LocalDateTime.now().plusHours(1);
        LocalDateTime arrival = departure.plusHours(3);

        flight.setExpectedDepartureTime(departure);
        flight.setExpectedArrivalTime(arrival);

        assertEquals(arrival, flight.getExpectedArrivalTime());
    }

    @Test
    void shouldRejectArrivalBeforeDeparture() {
        Flight flight = new Flight();
        LocalDateTime dep = LocalDateTime.now().plusHours(3);
        LocalDateTime arr = dep.minusHours(1);

        flight.setExpectedDepartureTime(dep);

        assertThrows(IllegalArgumentException.class, () ->
                flight.setExpectedArrivalTime(arr));
    }

    @Test
    void shouldSetOriginAndDestination() {
        Flight flight = new Flight();

        Airport waw = new Airport("WAW", "Warsaw", new Location("Poland", "Warsaw"));
        Airport krk = new Airport("KRK", "Krakow", new Location("Poland", "Kraków"));

        flight.setOrigin(waw);
        flight.setDestination(krk);

        assertSame(waw, flight.getOrigin());
        assertSame(krk, flight.getDestination());
    }

    @Test
    void shouldRejectNullOriginDestination() {
        Flight flight = new Flight();

        assertThrows(IllegalArgumentException.class, () -> flight.setOrigin(null));
        assertThrows(IllegalArgumentException.class, () -> flight.setDestination(null));
    }

    @Test
    void shouldSetAircraft() {
        Flight flight = new Flight();
        Aircraft ac = new TestAircraft();

        flight.setAircraft(ac);

        assertSame(ac, flight.getAircraft());
    }

    @Test
    void shouldRejectNullAircraft() {
        Flight flight = new Flight();

        assertThrows(IllegalArgumentException.class, () -> flight.setAircraft(null));
    }

    @Test
    void shouldSetStatus() {
        Flight flight = new Flight();

        flight.setStatus(FlightStatus.SCHEDULED);

        assertEquals(FlightStatus.SCHEDULED, flight.getStatus());
    }

    @Test
    void shouldRejectNullStatus() {
        Flight flight = new Flight();

        assertThrows(IllegalArgumentException.class, () -> flight.setStatus(null));
    }
}

