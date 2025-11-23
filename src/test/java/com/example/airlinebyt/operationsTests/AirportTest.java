package com.example.airlinebyt.operationsTests;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Test
    void testFields() {
        Airport a = new Airport();
        a.setId(1L);
        a.setIataCode("WAW");
        a.setName("Chopin Airport");
        a.setLocation(new Location("Poland", "Warsaw"));

        assertEquals(1L, a.getId());
        assertEquals("WAW", a.getIataCode());
        assertEquals("Chopin Airport", a.getName());
        assertEquals("Warsaw", a.getLocation().getCity());
    }
}
