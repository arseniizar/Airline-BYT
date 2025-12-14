package com.example.airlinebyt.associationTests;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PassengerBookingTest {
    //basic,bidirectional association

    @Test
    void shouldAddBookingToPassengerAndSetPassengerInBooking() {
        Passenger passenger = new Passenger(
                "John", "Doe", LocalDate.of(1990, 1, 1),
                "P123", "john@doe.com", "+123456789"
        );

        Booking booking = new Booking(BigDecimal.valueOf(500), passenger);

        assertTrue(passenger.getBookings().contains(booking));
        assertEquals(passenger, booking.getPassenger());
    }

    @Test
    void shouldRemoveBookingFromPassenger() {
        Passenger passenger = new Passenger(
                "John", "Doe", LocalDate.of(1990, 1, 1),
                "P123", "john@doe.com", "+123456789"
        );

        Booking booking = new Booking(BigDecimal.valueOf(500), passenger);

        passenger.removeBooking(booking);

        assertFalse(passenger.getBookings().contains(booking));
        assertNull(booking.getPassenger());
    }
}
