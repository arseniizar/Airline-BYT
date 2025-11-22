package com.example.airlinebyt.bookingTests;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testTicketFields() {
        Ticket ticket = new Ticket();

        Booking booking = new Booking();
        Flight flight = new Flight();
        Seat seat = new Seat();

        ticket.setId(10L);
        ticket.setTicketNumber("1");
        ticket.setPrice(new BigDecimal("400"));
        ticket.setBooking(booking);
        ticket.setFlight(flight);
        ticket.setSeat(seat);

        assertEquals(10L, ticket.getId());
        assertEquals("1", ticket.getTicketNumber());
        assertEquals(new BigDecimal("400"), ticket.getPrice());
        assertSame(booking, ticket.getBooking());
        assertSame(flight, ticket.getFlight());
        assertSame(seat, ticket.getSeat());
    }
    @Test
    void testNullValuesAllowed() {
        Ticket ticket = new Ticket();

        ticket.setTicketNumber(null);
        ticket.setPrice(null);
        ticket.setBooking(null);
        ticket.setFlight(null);
        ticket.setSeat(null);

        assertNull(ticket.getTicketNumber());
        assertNull(ticket.getPrice());
        assertNull(ticket.getBooking());
        assertNull(ticket.getFlight());
        assertNull(ticket.getSeat());
    }

}
