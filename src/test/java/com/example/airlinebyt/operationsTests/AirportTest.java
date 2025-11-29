package com.example.airlinebyt.operationsTests;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Test
    void testFields() {
        Airport a = new Airport(
                "WAW",
                "Chopin Airport",
                new Location("Poland", "Warsaw")
        );
        a.setId(1L);

        assertEquals(1L, a.getId());
        assertEquals("WAW", a.getIataCode());
        assertEquals("Chopin Airport", a.getName());
        assertEquals("Warsaw", a.getLocation().getCity());
    }
}
