package com.example.airlinebyt.peopleTests;

import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testPassengerFields() {
        Passenger p = new Passenger(
                "Passenger",
                "XYZ",
                LocalDate.now().minusYears(20),
                "1",
                "passenger@gmail.com",
                "+48987654321"
        );

        p.addLoyaltyPoints(150);

        assertEquals("Passenger", p.getFirstName());
        assertEquals("XYZ", p.getLastName());
        assertEquals("1", p.getPassengerID());
        assertEquals("passenger@gmail.com", p.getEmail());
        assertEquals("+48987654321", p.getContactNumber());
        assertEquals(150, p.getLoyaltyPoints());
    }

    @Test
    void testBookingsList() {
        Passenger p = new Passenger(
                "Passenger",
                "XYZ",
                LocalDate.now().minusYears(20),
                "1",
                "passenger@xyz.com",
                "+48987654321"
        );

        Booking b1 = new Booking();
        Booking b2 = new Booking();

        p.getBookings().add(b1);
        p.getBookings().add(b2);

        assertEquals(2, p.getBookings().size());
        assertTrue(p.getBookings().contains(b1));
        assertTrue(p.getBookings().contains(b2));
    }

    @Test
    void testPrintLoyaltyPoints() {
        Passenger p = new Passenger(
                "Passenger",
                "XYZ",
                LocalDate.now().minusYears(20),
                "1",
                "passenger@xyz.com",
                "+48987654321"
        );
        p.addLoyaltyPoints(150);

        p.printLoyaltyPoints();
        assertEquals(150, p.getLoyaltyPoints());
    }

    private Passenger passenger;
    private Booking booking1;
    private Booking booking2;

    @BeforeEach
    void setUpForAssociationTests() {
        passenger = new Passenger(
                "John", "Doe", LocalDate.of(1990, 1, 1),
                "PASS123", "john.doe@example.com", "+1234567890"
        );
        booking1 = new Booking(new BigDecimal("250.00"), null, new HashSet<>());
        booking2 = new Booking(new BigDecimal("350.00"), null, new HashSet<>());
    }

    @Test
    void addBooking_shouldAddBookingAndSetReverseConnection() {
        setUpForAssociationTests();
        passenger.addBooking(booking1);
        assertTrue(passenger.getBookings().contains(booking1));
        assertSame(passenger, booking1.getPassenger());
    }

    @Test
    void addBooking_shouldNotAddDuplicate() {
        setUpForAssociationTests();
        passenger.addBooking(booking1);
        passenger.addBooking(booking1);
        assertEquals(1, passenger.getBookings().size());
    }

    @Test
    void addBooking_shouldThrowExceptionForNullBooking() {
        setUpForAssociationTests();
        assertThrows(IllegalArgumentException.class, () -> passenger.addBooking(null));
    }

    @Test
    void removeBooking_shouldRemoveBookingAndBreakReverseConnection() {
        setUpForAssociationTests();
        passenger.addBooking(booking1);
        passenger.removeBooking(booking1);
        assertFalse(passenger.getBookings().contains(booking1));
        assertNull(booking1.getPassenger());
    }

    @Test
    void setPassengerInBooking_shouldUpdateAssociationsCorrectly() {
        setUpForAssociationTests();
        Passenger newPassenger = new Passenger(
                "Jane", "Smith", LocalDate.of(1992, 5, 5),
                "PASS456", "jane.smith@example.com", "+0987654321"
        );
        passenger.addBooking(booking1);
        booking1.setPassenger(newPassenger);
        assertFalse(passenger.getBookings().contains(booking1));
        assertTrue(newPassenger.getBookings().contains(booking1));
        assertSame(newPassenger, booking1.getPassenger());
    }
}
