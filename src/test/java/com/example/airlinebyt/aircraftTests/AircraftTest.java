package com.example.airlinebyt.aircraftTests;

import com.example.airlinebyt.models.aircraft.Aircraft;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    static class TestAircraft extends Aircraft {}

    @Test
    void testAircraftSettersAndGetters() {
        TestAircraft a = new TestAircraft();

        a.setId(1L);
        a.setModel("Plane 1");
        a.setCapacity(160);
        a.setType(AircraftType.COMMERCIAL);
        a.setCargoCapacity(500.0);
        a.setPersonalizedInterior("No");

        assertEquals(1L, a.getId());
        assertEquals("Plane 1", a.getModel());
        assertEquals(160, a.getCapacity());
        assertEquals(AircraftType.COMMERCIAL, a.getType());
        assertEquals(500.0, a.getCargoCapacity());
        assertEquals("No", a.getPersonalizedInterior());
    }
}

