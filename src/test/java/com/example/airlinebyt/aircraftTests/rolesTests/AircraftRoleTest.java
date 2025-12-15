package com.example.airlinebyt.aircraftTests.rolesTests;

import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AircraftRoleTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void commercialRoleShouldBeAssignableToAircraftRole() {
        AircraftRole role = new CommercialRole(200);

        assertInstanceOf(AircraftRole.class, role);
        assertInstanceOf(CommercialRole.class, role);
    }

    @Test
    void privateRoleShouldBeAssignableToAircraftRole() {
        AircraftRole role = new PrivateRole("Gold interior");

        assertInstanceOf(AircraftRole.class, role);
        assertInstanceOf(PrivateRole.class, role);
    }

    @Test
    void shouldDeserializeCommercialRoleFromJson() throws Exception {
        String json = """
                {"type":"commercial","cargoCapacity":500.0}
                """;

        AircraftRole role = mapper.readValue(json, AircraftRole.class);

        assertInstanceOf(CommercialRole.class, role);
        assertEquals(500.0, ((CommercialRole) role).getCargoCapacity());
    }

    @Test
    void shouldDeserializePrivateRoleFromJson() throws Exception {
        String json = """
                {"type":"private","personalizedInterior":"Yes"}
                """;

        AircraftRole role = mapper.readValue(json, AircraftRole.class);

        assertInstanceOf(PrivateRole.class, role);
        assertEquals("yes", ((PrivateRole) role).getPersonalizedInterior());
    }
}
