package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.models.booking.Luggage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LuggageTest {

    @Test
    void testTotalMaxWeight() {
        Luggage l = new Luggage();
        l.setNumberOfSuitcases(3);

        assertEquals(3 * Luggage.MAX_WEIGHT_PER_SUITCASE, l.getTotalMaxWeight());
    }
}

