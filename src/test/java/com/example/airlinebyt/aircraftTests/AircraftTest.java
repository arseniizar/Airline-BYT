package com.example.airlinebyt.aircraftTests;


import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import com.example.airlinebyt.models.booking.Seat;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    static class TestAircraft extends Aircraft {
        public TestAircraft(String model, int capacity, com.example.airlinebyt.models.aircraft.roles.AircraftRole role) {
            super(model, capacity, role);
        }
        @Override
        public String getType(){
            return "TestAircraft";
        }
    }

    @Test
    void shouldCreateAircraftWithValidValues() {
        TestAircraft aircraft = new TestAircraft("TestModel", 50, new CommercialRole(100));

        assertEquals("TestModel", aircraft.getModel());
        assertEquals(50, aircraft.getCapacity());
        assertTrue(aircraft.getRole() instanceof CommercialRole);
    }

    @Test
    void shouldSetValidModel() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(10));
        aircraft.setModel("NewModel");

        assertEquals("NewModel", aircraft.getModel());
    }

    @Test
    void shouldThrowExceptionForInvalidModel() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(10));

        assertThrows(IllegalArgumentException.class, () -> aircraft.setModel(""));
        assertThrows(IllegalArgumentException.class, () -> aircraft.setModel(null));
    }

    @Test
    void shouldSetValidCapacity() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(10));
        aircraft.setCapacity(200);

        assertEquals(200, aircraft.getCapacity());
    }

    @Test
    void shouldThrowExceptionForNegativeCapacity() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(10));

        assertThrows(IllegalArgumentException.class, () -> aircraft.setCapacity(-1));
    }

    @Test
    void shouldSetAndGetRole() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(10));
        PrivateRole newRole = new PrivateRole("Luxury");

        aircraft.setRole(newRole);

        assertSame(newRole, aircraft.getRole());
    }

    @Test
    void prepareForFlightShouldWorkForCommercialRole() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new CommercialRole(100));

        assertDoesNotThrow(aircraft::prepareForFlight);
    }

    @Test
    void prepareForFlightShouldWorkForPrivateRole() {
        TestAircraft aircraft = new TestAircraft("Model", 10, new PrivateRole("VIP interior"));

        assertDoesNotThrow(aircraft::prepareForFlight);
    }

    @Test
    void prepareForFlightShouldThrowExceptionForNullRole() {
        TestAircraft aircraft = new TestAircraft("Model", 10, null);

        assertThrows(IllegalStateException.class, aircraft::prepareForFlight);
    }

    @Test
    void addSeat_shouldCreateAndContainSeat() {
        Aircraft aircraft = new FixedWingAircraft("Boeing 737", 189, 34.3, 6, new CommercialRole(4500.0));
        aircraft.addSeat("1A", SeatClass.BUSINESS);
        Optional<Seat> seatOptional = aircraft.getSeat("1A");

        assertTrue(seatOptional.isPresent(), "Seat should be created and found.");
        Seat seat = seatOptional.get();
        assertEquals("1A", seat.getSeatNumber());
        assertEquals(SeatClass.BUSINESS, seat.getSeatClass());
    }

    @Test
    void addSeat_shouldThrowExceptionForEmptySeatNumberForComposition() {
        Aircraft aircraft = new FixedWingAircraft("Boeing 737", 189, 34.3, 6, new CommercialRole(4500.0));
        assertThrows(IllegalArgumentException.class, () -> aircraft.addSeat("  ", SeatClass.ECONOMY));
    }

    @Test
    void seatConstructor_shouldThrowExceptionWhenCreatedWithoutAircraft() {
        assertThrows(IllegalArgumentException.class, () -> new Seat("3C", SeatClass.ECONOMY, null));
    }
}

