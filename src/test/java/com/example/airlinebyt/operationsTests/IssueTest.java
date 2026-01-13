package com.example.airlinebyt.operationsTests;

import com.example.airlinebyt.enums.ConstructionType;
import com.example.airlinebyt.enums.RoleType;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.operations.Issue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueTest {

    @Test
    void shouldCreateIssueWithValidData() {
        // Оновлене створення
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        Issue issue = new Issue("Engine overheating", aircraft);

        assertEquals("Engine overheating", issue.getDescription());
        assertFalse(issue.isResolved());
        assertSame(aircraft, issue.getSource());
    }

    @Test
    void shouldRejectEmptyDescription() {
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        assertThrows(IllegalArgumentException.class, () -> new Issue("", aircraft));
        assertThrows(IllegalArgumentException.class, () -> new Issue("   ", aircraft));
        assertThrows(IllegalArgumentException.class, () -> new Issue(null, aircraft));
    }

    @Test
    void shouldRejectNullAircraft() {
        assertThrows(IllegalArgumentException.class, () -> new Issue("Something broke", null));
    }

    @Test
    void shouldMarkIssueAsResolved() {
        Aircraft aircraft = new Aircraft("TestPlane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);
        Issue issue = new Issue("Test", aircraft);

        issue.markAsResolved();

        assertTrue(issue.isResolved());
    }
}
