package com.example.airlinebyt.peopleTests;
import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.models.person.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testPassengerFields() {
        Passenger p = new Passenger();

        p.setFirstName("Passenger");
        p.setLastName("XYZ");
        p.setPassengerID("1");
        p.setEmail("passenger@gmail.com");
        p.setContactNumber("123456789");
        p.setLoyaltyPoints(150);

        assertEquals("Passenger", p.getFirstName());
        assertEquals("XYZ", p.getLastName());
        assertEquals("1", p.getPassengerID());
        assertEquals("passenger@gmail.com", p.getEmail());
        assertEquals("123456789", p.getContactNumber());
        assertEquals(150, p.getLoyaltyPoints());
    }

    @Test
    void testBookingsList() {
        Passenger p = new Passenger();

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
        Passenger p = new Passenger();
        p.setLoyaltyPoints(150);

        p.printLoyaltyPoints();
        assertEquals(150, p.getLoyaltyPoints());
    }

}
