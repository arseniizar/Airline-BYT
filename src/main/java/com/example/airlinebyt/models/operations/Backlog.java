package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Backlog implements BaseEntity {

    private Long id;

    private Aircraft aircraft;

    private List<Issue> listOfIssues = new ArrayList<>();

    public Backlog(Aircraft aircraft) {
        setAircraft(aircraft);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Backlog must be associated with an aircraft.");
        }
        this.aircraft = aircraft;
    }

    public void addIssue(Issue issue) {
        if (issue == null) {
            throw new IllegalArgumentException("Cannot add a null issue to the backlog.");
        }
        this.listOfIssues.add(issue);
    }
}
