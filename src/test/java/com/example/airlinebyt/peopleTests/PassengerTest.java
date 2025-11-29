package com.example.airlinebyt.peopleTests;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

}
