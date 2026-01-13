package com.example.airlinebyt.operationsTests;

import com.example.airlinebyt.enums.ConstructionType;
import com.example.airlinebyt.enums.RoleType;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.operations.Issue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacklogTest {

    @Test
    void shouldCreateBacklogWithValidAircraft() {
        // Оновлене створення
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        Backlog backlog = new Backlog(aircraft);

        assertSame(aircraft, backlog.getAircraft());
        assertTrue(backlog.getListOfIssues().isEmpty());
    }

    @Test
    void shouldRejectNullAircraft() {
        assertThrows(IllegalArgumentException.class, () -> new Backlog(null));
    }

    @Test
    void shouldAddIssue() {
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);
        Backlog backlog = new Backlog(aircraft);

        Issue issue = new Issue("Broken flaps", aircraft);

        backlog.addIssue(issue);

        assertEquals(1, backlog.getListOfIssues().size());
        assertSame(issue, backlog.getListOfIssues().get(0));
    }

    @Test
    void shouldRejectNullIssue() {
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);
        Backlog backlog = new Backlog(aircraft);

        assertThrows(IllegalArgumentException.class, () -> backlog.addIssue(null));
    }
}
