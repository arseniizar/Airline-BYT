package com.example.airlinebyt.aircraftTests;

import com.example.airlinebyt.models.aircraft.RotorcraftAircraft;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotorcraftAircraftTest {

    @Test
    void testFields() {
        RotorcraftAircraft r = new RotorcraftAircraft();

        r.setAmountOfRotors(4);
        r.setMaxHoverAltitude(3500.0);

        assertEquals(4, r.getAmountOfRotors());
        assertEquals(3500.0, r.getMaxHoverAltitude());
    }
}

