package com.example.airlinebyt.associationTests;

import com.example.airlinebyt.models.aircraft.FixedWingAircraft;
import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.operations.Issue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacklogIssueTest {
    //aggregation association

    @Test
    void shouldAddIssueToBacklog() {
        FixedWingAircraft aircraft =
                new FixedWingAircraft("Boeing 737", 180, 35.8, 6, null);

        Backlog backlog = new Backlog(aircraft);
        Issue issue = new Issue("Engine check", aircraft);

        backlog.addIssue(issue);

        assertEquals(1, backlog.getListOfIssues().size());
        assertEquals(issue, backlog.getListOfIssues().get(0));
    }
}
