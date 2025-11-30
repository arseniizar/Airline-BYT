package com.example.airlinebyt.aircraftTests;


import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import org.junit.jupiter.api.Test;
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
}

