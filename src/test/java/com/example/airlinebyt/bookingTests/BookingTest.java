package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Luggage;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.models.embeddable.Location;
import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Passenger passenger;
    private Flight flight;
    private Seat seat1, seat2;
    private Aircraft aircraft;
    private Airport waw;
    private Airport jfk;

    @BeforeEach
    void setUp() {
        passenger = new Passenger(
                "John", "Doe", LocalDate.now().minusYears(20),
                "P123456", "john.doe@gmail.com", "+48123456789"
        );

        waw = new Airport("WAW", "Warsaw", new Location("PL", "Warsaw"));
        jfk = new Airport("JFK", "New York", new Location("US", "NY"));

        aircraft = new FixedWingAircraft("Test Model", 100, 30.0, 4, new CommercialRole(1000.0));
        flight = new Flight("FL123", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), FlightStatus.SCHEDULED, aircraft, waw, jfk);

        seat1 = new Seat("1A", SeatClass.ECONOMY, aircraft);
        seat2 = new Seat("1B", SeatClass.BUSINESS, aircraft);
    }


    @Test
    void testBookingFields() {
        Booking booking = new Booking(new BigDecimal("350.00"), passenger, new HashSet<>());

        Luggage luggage = new Luggage(2);

        booking.setId(5L);
        booking.setPaymentMethod(PaymentMethod.ONLINE_GATEWAY);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingFee(new BigDecimal("50.00"));
        booking.setLuggage(luggage);

        assertEquals(5L, booking.getId());
        assertEquals(PaymentMethod.ONLINE_GATEWAY, booking.getPaymentMethod());
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus());
        assertEquals(new BigDecimal("50.00"), booking.getBookingFee());
        assertEquals(new BigDecimal("350.00"), booking.getTotalPrice());
        assertSame(passenger, booking.getPassenger());
        assertSame(luggage, booking.getLuggage());
    }

    @Test
    void testTicketsList() {
        Booking booking = new Booking(new BigDecimal("500.00"), passenger, new HashSet<>());

        Ticket t1 = booking.createTicket(new BigDecimal("250.00"), flight, seat1);
        Ticket t2 = booking.createTicket(new BigDecimal("250.00"), flight, seat2);

        Set<Ticket> tickets = booking.getTickets();
        assertEquals(2, tickets.size());
        assertTrue(tickets.contains(t1));
        assertTrue(tickets.contains(t2));

        assertSame(booking, t1.getBooking());
        assertSame(booking, t2.getBooking());
    }

    @Test
    void testNullValuesAllowed() {
        Booking booking = new Booking(new BigDecimal("100.00"), passenger, new HashSet<>());

        booking.setPaymentMethod(null);
        // booking.setBookingStatus(null);
        booking.setBookingFee(null);
        booking.setLuggage(null);

        assertNull(booking.getPaymentMethod());
        assertNull(booking.getBookingFee());
        assertNull(booking.getLuggage());
        assertNotNull(booking.getTickets());
    }
}
