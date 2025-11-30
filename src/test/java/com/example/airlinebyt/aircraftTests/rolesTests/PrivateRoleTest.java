package com.example.airlinebyt.aircraftTests.rolesTests;

import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrivateRoleTest {

    @Test
    void shouldCreatePrivateRoleWithValidInterior() {
        PrivateRole role = new PrivateRole("Luxury leather");

        assertEquals("Luxury leather", role.getPersonalizedInterior());
    }

    @Test
    void shouldThrowExceptionIfInteriorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new PrivateRole(null));
    }

    @Test
    void shouldThrowExceptionIfInteriorIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new PrivateRole("   "));
    }

    @Test
    void shouldSetValidInterior() {
        PrivateRole role = new PrivateRole();
        role.setPersonalizedInterior("Premium wood finish");

        assertEquals("Premium wood finish", role.getPersonalizedInterior());
    }
}
