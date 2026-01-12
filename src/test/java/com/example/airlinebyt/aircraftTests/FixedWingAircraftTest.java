package com.example.airlinebyt.aircraftTests;


import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedWingAircraftTest {

    @Test
    void shouldCreateFixedWingAircraftWithValidValues() {
        CommercialRole role = new CommercialRole(2000);
        FixedWingAircraft aircraft = new FixedWingAircraft("Boeing 747", 300, 60.0, 14, role);

        assertEquals("Boeing 747", aircraft.getModel());
        assertEquals(300, aircraft.getCapacity());
        assertEquals(60.0, aircraft.getWingLength());
        assertEquals(14, aircraft.getLandingWheels());
        assertEquals(role, aircraft.getRole());
    }

    @Test
    void shouldThrowExceptionForInvalidWingLength() {
        assertThrows(IllegalArgumentException.class, () ->
                new FixedWingAircraft("Boeing", 200, -1.0, 10, new CommercialRole()));
    }

    @Test
    void shouldThrowExceptionForInvalidLandingWheels() {
        assertThrows(IllegalArgumentException.class, () ->
                new FixedWingAircraft("Boeing", 200, 20.0, 0, new CommercialRole()));
    }
}


