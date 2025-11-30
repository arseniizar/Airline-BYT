package com.example.airlinebyt.operationsTests;


import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.operations.Issue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueTest {

    static class TestAircraft extends Aircraft {
        public TestAircraft() {
            super("TestPlane", 100, new CommercialRole(500));
        }

        @Override
        public String getType() {
            return "";
        }
    }

    @Test
    void shouldCreateIssueWithValidData() {
        Aircraft aircraft = new TestAircraft();

        Issue issue = new Issue("Engine overheating", aircraft);

        assertEquals("Engine overheating", issue.getDescription());
        assertFalse(issue.isResolved());
        assertSame(aircraft, issue.getSource());
    }

    @Test
    void shouldRejectEmptyDescription() {
        Aircraft aircraft = new TestAircraft();

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
        Aircraft aircraft = new TestAircraft();
        Issue issue = new Issue("Test", aircraft);

        issue.markAsResolved();

        assertTrue(issue.isResolved());
    }
}
