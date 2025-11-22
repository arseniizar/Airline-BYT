package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.booking.Seat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void testFields() {
        Seat s = new Seat();

        s.setSeatNumber("18A");
        s.setSeatClass(SeatClass.ECONOMY);

        assertEquals("18A", s.getSeatNumber());
        assertEquals(SeatClass.ECONOMY, s.getSeatClass());
    }
}

