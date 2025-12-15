package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Booking booking;
    private Flight flight;
    private Seat seat;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        Passenger passenger = new Passenger(
                "Test", "Passenger", LocalDate.now().minusYears(30),
                "TP123", "test@test.com", "+123456789"
        );

        Aircraft aircraft = new FixedWingAircraft("Test Plane", 1, 10.0, 2, null);

        booking = new Booking(new BigDecimal("500.00"), passenger, new HashSet<>());
        flight = new Flight("FL987", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(3), null, null, null, null);
        seat = new Seat("7G", null, aircraft);

        String ticketNumber = UUID.randomUUID().toString();
        BigDecimal price = new BigDecimal("400");

        ticket = booking.createTicket(price, flight, seat);
    }

    @Test
    void testTicketFieldsAfterCreation() {
        assertNotNull(ticket.getId(), "ID має бути згенерований, якщо у вас є така логіка, інакше він буде null.");
        assertNotNull(ticket.getTicketNumber(), "Ticket number should be generated and not null.");
        assertEquals(new BigDecimal("400"), ticket.getPrice(), "Price should match the one provided during creation.");

        assertSame(booking, ticket.getBooking(), "The ticket must be associated with the correct booking.");
        assertSame(flight, ticket.getFlight(), "The ticket must be associated with the correct flight.");
        assertSame(seat, ticket.getSeat(), "The ticket must be associated with the correct seat.");
    }

    @Test
    void testSettersForMutableFields() {
        BigDecimal newPrice = new BigDecimal("450.50");
        ticket.setPrice(newPrice);
        assertEquals(newPrice, ticket.getPrice(), "Price should be updatable.");

        Seat newSeat = new Seat("8A", null, flight.getAircraft());
        ticket.setSeat(newSeat);
        assertSame(newSeat, ticket.getSeat(), "Seat should be updatable.");
    }

    @Test
    void testValidationInSetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ticket.setPrice(null);
        }, "Setting a null price should throw an exception.");

        assertThrows(IllegalArgumentException.class, () -> {
            ticket.setPrice(new BigDecimal("-100"));
        }, "Setting a negative price should throw an exception.");

        assertThrows(IllegalArgumentException.class, () -> {
            ticket.setFlight(null);
        }, "Setting a null flight should throw an exception.");
    }
}
