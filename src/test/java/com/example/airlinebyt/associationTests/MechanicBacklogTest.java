package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.person.Mechanic;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MechanicBacklogTest {

    @Test
    void shouldAssignBacklogToMechanic() {
        Mechanic mechanic = new Mechanic(
                "Mike", "Wrench",
                LocalDate.of(1985, 3, 3),
                LocalDate.of(2015, 1, 1),
                "Engineering",
                4000.0
        );

        FixedWingAircraft aircraft =
                new FixedWingAircraft("Cessna 172", 4, 11.0, 3, null);

        Backlog backlog = new Backlog(aircraft);

        mechanic.assignBacklog(backlog);

        assertTrue(mechanic.getAssignedBacklogs().contains(backlog));
    }
}

