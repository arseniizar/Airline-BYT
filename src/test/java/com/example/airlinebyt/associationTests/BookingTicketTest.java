package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.booking.*;
import com.example.airlinebyt.models.operations.Flight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookingTicketTest {
    //composition

    @Test
    void shouldCreateTicketAndAssignToBooking() {
        Booking booking = new Booking(BigDecimal.valueOf(300), null);
        Flight flight = new Flight();
        Seat seat = new Seat("12A", SeatClass.ECONOMY, null);

        Ticket ticket = booking.createTicket(
                BigDecimal.valueOf(300), flight, seat
        );

        assertEquals(booking, ticket.getBooking());
        assertTrue(booking.getTickets().contains(ticket));
    }

    @Test
    void shouldRemoveTicketFromBooking() {
        Booking booking = new Booking(BigDecimal.valueOf(300), null);
        Flight flight = new Flight();
        Seat seat = new Seat("12A", SeatClass.ECONOMY, null);

        Ticket ticket = booking.createTicket(
                BigDecimal.valueOf(300), flight, seat
        );

        booking.removeTicket(ticket);

        assertFalse(booking.getTickets().contains(ticket));
    }
}
