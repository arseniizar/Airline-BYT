package com.example.airlinebyt.operationsTests;


import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.operations.Issue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacklogTest {

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
    void shouldCreateBacklogWithValidAircraft() {
        Aircraft aircraft = new TestAircraft();
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
        Aircraft aircraft = new TestAircraft();
        Backlog backlog = new Backlog(aircraft);

        Issue issue = new Issue("Broken flaps", aircraft);

        backlog.addIssue(issue);

        assertEquals(1, backlog.getListOfIssues().size());
        assertSame(issue, backlog.getListOfIssues().get(0));
    }

    @Test
    void shouldRejectNullIssue() {
        Aircraft aircraft = new TestAircraft();
        Backlog backlog = new Backlog(aircraft);

        assertThrows(IllegalArgumentException.class, () -> backlog.addIssue(null));
    }
}

