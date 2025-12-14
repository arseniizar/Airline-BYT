package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.enums.SeatClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatAircraftTest {

    @Test
    void shouldAssignAircraftToSeat() {
        FixedWingAircraft aircraft =
                new FixedWingAircraft("Airbus A320", 180, 34.1, 6, null);

        Seat seat = new Seat("10A", SeatClass.ECONOMY, aircraft);

        assertEquals(aircraft, seat.getAircraft());
    }
}

