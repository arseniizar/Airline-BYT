package com.example.airlinebyt.bookingTests;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.booking.Luggage;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingFields() {
        Booking booking = new Booking();

        Passenger passenger = new Passenger(
                "John",
                "Doe",
                LocalDate.now().minusYears(20),
                "P123456",
                "john.doe@gmail.com",
                "+48123456789"
        );
        Luggage luggage = new Luggage();

        booking.setId(5L);
        booking.setPaymentMethod(PaymentMethod.ONLINE_GATEWAY);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingFee(new BigDecimal("50.00"));
        booking.setTotalPrice(new BigDecimal("350.00"));
        booking.setPassenger(passenger);
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
        Booking booking = new Booking();
        Ticket t1 = new Ticket();
        Ticket t2 = new Ticket();

        t1.setBooking(booking);
        t2.setBooking(booking);

        booking.getTickets().add(t1);
        booking.getTickets().add(t2);

        assertEquals(2, booking.getTickets().size());
        assertTrue(booking.getTickets().contains(t1));
        assertTrue(booking.getTickets().contains(t2));
    }

    @Test
    void testNullValuesAllowed() {
        Booking booking = new Booking();

        booking.setPaymentMethod(null);
        booking.setBookingStatus(null);
        booking.setBookingFee(null);
        booking.setTotalPrice(null);
        booking.setPassenger(null);
        booking.setLuggage(null);
        booking.setTickets(new ArrayList<>());

        assertNull(booking.getPaymentMethod());
        assertNull(booking.getBookingStatus());
        assertNull(booking.getBookingFee());
        assertNull(booking.getTotalPrice());
        assertNull(booking.getPassenger());
        assertNull(booking.getLuggage());
        assertNotNull(booking.getTickets());
    }
}

