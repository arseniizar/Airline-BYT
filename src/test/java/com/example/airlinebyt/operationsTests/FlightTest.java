package com.example.airlinebyt.operationsTests;

import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Pilot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    static class TestAircraft extends Aircraft {
        public TestAircraft() {
            super("A320", 180, new CommercialRole(700));
        }

        @Override
        public String getType() {
            return "fixed_wing";
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
        assertThrows(IllegalArgumentException.class, () -> flight.setExpectedDepartureTime(past));
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
        assertThrows(IllegalArgumentException.class, () -> flight.setExpectedArrivalTime(arr));
    }

    @Test
    void shouldSetOriginAndDestination() {
        Flight flight = new Flight();
        Airport waw = new Airport("WAW", "Warsaw", new Location("Poland", "Warsaw"));
        Airport krk = new Airport("KRK", "Krakow", new Location("Poland", "Kraków"));
        flight.setOrigin(waw);
        flight.setDestination(krk);
        assertSame(waw, flight.getOriginMetadata().getAirport());
        assertSame(krk, flight.getDestinationMetadata().getAirport());
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

    private Flight flight1, flight2, flight3;
    private Airport waw, jfk;
    private Aircraft aircraftForTest;
    private Pilot pilot1;

    @BeforeEach
    void setUpForAssociationTests() {
        waw = new Airport("WAW", "Warsaw Chopin", new Location("Poland", "Warsaw"));
        jfk = new Airport("JFK", "John F. Kennedy", new Location("USA", "New York"));
        aircraftForTest = new FixedWingAircraft("Boeing 787", 290, 60.1, 10, new CommercialRole(10000.0));

        LocalDateTime departureTime1 = LocalDateTime.now().plusDays(10);
        flight1 = new Flight("LO001", departureTime1, departureTime1.plusHours(8), FlightStatus.SCHEDULED, aircraftForTest, waw, jfk);

        LocalDateTime departureTime2 = departureTime1.plusHours(10);
        flight2 = new Flight("LO002", departureTime2, departureTime2.plusHours(3), FlightStatus.SCHEDULED, aircraftForTest, jfk, waw);

        LocalDateTime departureTime3 = LocalDateTime.now().plusDays(15);
        flight3 = new Flight("LO003", departureTime3, departureTime3.plusHours(5), FlightStatus.SCHEDULED, aircraftForTest, waw, jfk);

        pilot1 = new Pilot(
                "Tom", "Cruise", LocalDate.of(1962, 7, 3),
                LocalDate.of(2005, 5, 1), "Top Gun Academy",
                "LIC12345", LocalDate.now().plusYears(5), 150000.0
        );
    }

    @Test
    void setNextLeg_shouldEstablishBidirectionalLink() {
        flight1.setNextLeg(flight2);
        assertSame(flight2, flight1.getNextLeg(), "flight1's next leg should be flight2.");
        assertSame(flight1, flight2.getPreviousLeg(), "flight2's previous leg should be flight1 (reverse connection).");
    }

    @Test
    void setNextLeg_shouldBreakOldLinkWhenSettingNewOne() {
        flight1.setNextLeg(flight2);
        flight1.setNextLeg(flight3);

        assertSame(flight3, flight1.getNextLeg(), "flight1's new next leg should be flight3.");
        assertSame(flight1, flight3.getPreviousLeg(), "flight3's previous leg should be flight1.");
        assertNull(flight2.getPreviousLeg(), "Old link should be broken, flight2 should have no previous leg.");
    }

    @Test
    void setNextLeg_toNullShouldBreakTheLink() {
        flight1.setNextLeg(flight2);
        flight1.setNextLeg(null);

        assertNull(flight1.getNextLeg(), "flight1 should have no next leg.");
        assertNull(flight2.getPreviousLeg(), "flight2 should have no previous leg.");
    }

    @Test
    void setNextLeg_shouldThrowExceptionForSelfReference() {
        assertThrows(IllegalArgumentException.class, () -> flight1.setNextLeg(flight1), "A flight cannot be its own leg.");
    }

    @Test
    void assignPilot_shouldAddPilotWithRoleAndCreateReverseConnection() {
        flight1.assignPilot(pilot1, "Captain");

        assertTrue(flight1.getPilots().contains(pilot1), "Pilot should be in the flight's pilot set.");
        assertEquals("Captain", flight1.getPilotRoles().get(pilot1), "Pilot should have the specified role.");
        assertTrue(pilot1.getFlights().contains(flight1), "Reverse connection: Flight should be in pilot's flight set.");
    }

    @Test
    void unassignPilot_shouldRemovePilotAndBreakReverseConnection() {
        flight1.assignPilot(pilot1, "Captain");
        flight1.unassignPilot(pilot1);

        assertFalse(flight1.getPilots().contains(pilot1), "Pilot should be removed from flight.");
        assertFalse(pilot1.getFlights().contains(flight1), "Reverse connection should be broken.");
    }

    @Test
    void assignPilot_shouldThrowExceptionForNullArgs() {
        assertThrows(IllegalArgumentException.class, () -> flight1.assignPilot(null, "Captain"));
        assertThrows(IllegalArgumentException.class, () -> flight1.assignPilot(pilot1, null));
        assertThrows(IllegalArgumentException.class, () -> flight1.assignPilot(pilot1, "  "));
    }

    @Test
    void constructor_shouldCreateOriginAndDestinationMetadata() {
        assertNotNull(flight1.getOriginMetadata(), "Origin metadata should be created by constructor.");
        assertSame(flight1, flight1.getOriginMetadata().getFlight(), "Metadata must link to its flight.");
        assertSame(waw, flight1.getOriginMetadata().getAirport(), "Metadata must link to the correct origin airport.");

        assertNotNull(flight1.getDestinationMetadata(), "Destination metadata should be created by constructor.");
        assertSame(flight1, flight1.getDestinationMetadata().getFlight());
        assertSame(jfk, flight1.getDestinationMetadata().getAirport());
    }

    @Test
    void setOrigin_shouldUpdateMetadata() {
        Airport lhr = new Airport("LHR", "Heathrow", new Location("UK", "London"));
        flight1.setOrigin(lhr);

        assertSame(lhr, flight1.getOriginMetadata().getAirport(), "Origin airport should be updated via setter.");
    }
}
