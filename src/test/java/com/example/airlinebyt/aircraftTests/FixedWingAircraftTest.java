package com.example.airlinebyt.aircraftTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FixedWingAircraftTest {

    @Test
    void testFields() {
        FixedWingAircraft fw = new FixedWingAircraft();

        fw.setWingLength(36.0);
        fw.setLandingWheels(6);

        assertEquals(36, fw.getWingLength());
        assertEquals(6, fw.getLandingWheels());
    }
}

