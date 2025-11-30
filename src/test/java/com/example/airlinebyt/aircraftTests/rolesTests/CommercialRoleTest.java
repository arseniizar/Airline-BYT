package com.example.airlinebyt.aircraftTests.rolesTests;

import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommercialRoleTest {

    @Test
    void shouldCreateCommercialRoleWithValidCargoCapacity() {
        CommercialRole role = new CommercialRole(500.0);

        assertEquals(500.0, role.getCargoCapacity());
    }

    @Test
    void shouldThrowExceptionForNegativeCargoCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new CommercialRole(-10));
    }

    @Test
    void shouldSetValidCargoCapacity() {
        CommercialRole role = new CommercialRole();
        role.setCargoCapacity(300.0);

        assertEquals(300.0, role.getCargoCapacity());
    }
}

