package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightLegTest {

    @Test
    void shouldLinkFlightsAsLegs() {
        Flight leg1 = new Flight();
        Flight leg2 = new Flight();

        leg1.setNextLeg(leg2);

        assertEquals(leg2, leg1.getNextLeg());
        assertEquals(leg1, leg2.getPreviousLeg());
    }

    @Test
    void shouldUnlinkPreviousLegWhenChanged() {
        Flight leg1 = new Flight();
        Flight leg2 = new Flight();
        Flight leg3 = new Flight();

        leg1.setNextLeg(leg2);
        leg1.setNextLeg(leg3);

        assertNull(leg2.getPreviousLeg());
        assertEquals(leg3, leg1.getNextLeg());
        assertEquals(leg1, leg3.getPreviousLeg());
    }
}
