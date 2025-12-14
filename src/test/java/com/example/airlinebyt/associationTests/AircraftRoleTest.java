package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftRoleTest {

    @Test
    void shouldAssignRoleToAircraft() {
        CommercialRole role = new CommercialRole(5000);

        FixedWingAircraft aircraft =
                new FixedWingAircraft("Boeing 777", 300, 64.8, 12, role);

        assertEquals(role, aircraft.getRole());
    }
}

