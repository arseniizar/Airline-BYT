package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Luggage;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void shouldSetBasicBookingFields() {
        Booking booking = new Booking(
                BigDecimal.valueOf(300),
                new Passenger(
                        "John",
                        "Doe",
                        LocalDate.now().minusYears(20),
                        "P123456",
                        "john.doe@gmail.com",
                        "+48123456789"
                )
        );

        booking.setId(5L);
        booking.setPaymentMethod(PaymentMethod.ONLINE_GATEWAY);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingFee(BigDecimal.valueOf(50));

        assertEquals(5L, booking.getId());
        assertEquals(PaymentMethod.ONLINE_GATEWAY, booking.getPaymentMethod());
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus());
        assertEquals(BigDecimal.valueOf(50), booking.getBookingFee());
        assertEquals(BigDecimal.valueOf(300), booking.getTotalPrice());
        assertNotNull(booking.getPassenger());
    }

    @Test
    void shouldCreateTicketsViaComposition() {
        Booking booking = new Booking(BigDecimal.valueOf(500), null);

        Flight flight = new Flight();
        Seat seat1 = new Seat("12A", SeatClass.ECONOMY, null);
        Seat seat2 = new Seat("12B", SeatClass.ECONOMY, null);

        Ticket t1 = booking.createTicket(
                BigDecimal.valueOf(250), flight, seat1
        );

        Ticket t2 = booking.createTicket(
                BigDecimal.valueOf(250), flight, seat2
        );

        assertEquals(2, booking.getTickets().size());
        assertTrue(booking.getTickets().contains(t1));
        assertTrue(booking.getTickets().contains(t2));

        assertEquals(booking, t1.getBooking());
        assertEquals(booking, t2.getBooking());
    }

    @Test
    void shouldAssignLuggageToBooking() {
        Booking booking = new Booking(BigDecimal.valueOf(200), null);
        Luggage luggage = new Luggage(2);

        booking.setLuggage(luggage);
        luggage.setBooking(booking);

        assertEquals(luggage, booking.getLuggage());
        assertEquals(booking, luggage.getBooking());
    }

    @Test
    void shouldRemoveTicketFromBooking() {
        Booking booking = new Booking(BigDecimal.valueOf(300), null);

        Flight flight = new Flight();
        Seat seat = new Seat("1A", SeatClass.BUSINESS, null);

        Ticket ticket = booking.createTicket(
                BigDecimal.valueOf(300), flight, seat
        );

        booking.removeTicket(ticket);

        assertTrue(booking.getTickets().isEmpty());
    }
}
