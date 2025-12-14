package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Luggage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookingLuggageTest {

    @Test
    void shouldAssignLuggageToBooking() {
        Booking booking = new Booking(BigDecimal.valueOf(200), null);
        Luggage luggage = new Luggage(2);

        booking.setLuggage(luggage);
        luggage.setBooking(booking);

        assertEquals(luggage, booking.getLuggage());
        assertEquals(booking, luggage.getBooking());
    }
}

