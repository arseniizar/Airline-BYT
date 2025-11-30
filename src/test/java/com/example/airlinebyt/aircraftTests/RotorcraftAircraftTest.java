package com.example.airlinebyt.aircraftTests;



import com.example.airlinebyt.models.aircraft.RotorcraftAircraft;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotorcraftAircraftTest {

    @Test
    void shouldCreateRotorcraftWithValidValues() {
        PrivateRole role = new PrivateRole("VIP interior");
        RotorcraftAircraft aircraft = new RotorcraftAircraft("model1", 2, 4, 800.0, role);

        assertEquals("model1", aircraft.getModel());
        assertEquals(2, aircraft.getCapacity());
        assertEquals(4, aircraft.getAmountOfRotors());
        assertEquals(800.0, aircraft.getMaxHoverAltitude());
        assertEquals(role, aircraft.getRole());
    }

    @Test
    void shouldThrowExceptionForInvalidRotorCount() {
        assertThrows(IllegalArgumentException.class, () ->
                new RotorcraftAircraft("Model", 4, 0, 100.0, new PrivateRole("Test")));
    }

    @Test
    void shouldThrowExceptionForInvalidHoverAltitude() {
        assertThrows(IllegalArgumentException.class, () ->
                new RotorcraftAircraft("Model", 4, 2, -10.0, new PrivateRole("Test")));
    }
}


