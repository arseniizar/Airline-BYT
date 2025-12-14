package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.person.CrewMember;
import com.example.airlinebyt.models.person.Pilot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
public class Flight implements BaseEntity {
    @Getter @Setter private Long id;
    @Getter private String flightNumber;
    @Getter private LocalDateTime expectedDepartureTime;
    @Getter private LocalDateTime expectedArrivalTime;
    @Getter @Setter private LocalDateTime actualDepartureTime;
    @Getter @Setter private LocalDateTime actualArrivalTime;
    @Getter private FlightStatus status;
    @Getter private Aircraft aircraft;
    @Getter private Airport origin;
    @Getter private Airport destination;
    private Set<CrewMember> crew = new HashSet<>();
    private Map<Pilot, String> pilotRoles = new HashMap<>();
    @Getter private Flight previousLeg;
    @Getter private Flight nextLeg;

    // --- ASSOCIATION MANAGEMENT ---

    public void addCrewMember(CrewMember member) {
        if (member == null) throw new IllegalArgumentException("Crew member cannot be null.");
        if (!this.crew.contains(member)) {
            this.crew.add(member);
            member.assignToFlight(this);
        }
    }

    public void removeCrewMember(CrewMember member) {
        if (member != null && this.crew.contains(member)) {
            this.crew.remove(member);
            member.removeFromFlight(this);
        }
    }

    public void assignPilot(Pilot pilot, String role) {
        if (pilot == null || role == null || role.isBlank()) throw new IllegalArgumentException("Pilot and role are required.");
        if (!this.pilotRoles.containsKey(pilot)) {
            this.pilotRoles.put(pilot, role);
            pilot.addFlight(this);
        }
    }

    public void unassignPilot(Pilot pilot) {
        if (pilot != null && this.pilotRoles.containsKey(pilot)) {
            this.pilotRoles.remove(pilot);
            pilot.removeFlight(this);
        }
    }

    public void setOrigin(Airport origin) {
        if (origin == null) throw new IllegalArgumentException("Origin airport cannot be null.");
        if (this.origin == null || !this.origin.equals(origin)) {
            if (this.origin != null) this.origin.removeDeparture(this);
            this.origin = origin;
            this.origin.addDeparture(this);
        }
    }

    public void setNextLeg(Flight next) {
        if (next == this) throw new IllegalArgumentException("Flight cannot be its own next leg.");
        if (this.nextLeg != next) {
            if (this.nextLeg != null) this.nextLeg.previousLeg = null;
            this.nextLeg = next;
            if (next != null) next.setPreviousLeg(this);
        }
    }

    private void setPreviousLeg(Flight prev) {
        if (this.previousLeg != prev) {
            this.previousLeg = prev;
            if (prev != null) prev.setNextLeg(this);
        }
    }

    // --- GETTERS & STANDARD METHODS ---
    public Set<CrewMember> getCrew() { return Collections.unmodifiableSet(crew); }
    public Map<Pilot, String> getPilotRoles() { return Collections.unmodifiableMap(pilotRoles); }
    public Set<Pilot> getPilots() { return Collections.unmodifiableSet(pilotRoles.keySet()); }

    public void setDestination(Airport destination) {
        if (destination == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".destination cannot be empty");
        }
        this.destination = destination;
    }

    public void setFlightNumber(String flightNumber) {
        if (flightNumber == null  || flightNumber.isEmpty()) {
            throw new  IllegalArgumentException(this.getClass().getName() + ".flightNumber cannot be empty");
        }
        this.flightNumber = flightNumber;
    }

    public void setExpectedDepartureTime(LocalDateTime expectedDepartureTime) {
        if (expectedDepartureTime == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".expectedDepartureTime cannot be empty");
        }
        if (expectedDepartureTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(this.getClass().getName() + ".expectedDepartureTime cannot be in the past");
        }
        this.expectedDepartureTime = expectedDepartureTime;
    }

    public void setExpectedArrivalTime(LocalDateTime expectedArrivalTime) {
        if (expectedArrivalTime == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".expectedArrivalTime cannot be empty");
        }
        if (this.expectedDepartureTime != null && expectedArrivalTime.isBefore(this.expectedDepartureTime)) {
            throw new IllegalArgumentException(this.getClass().getName() + ".expectedArrivalTime cannot be before expectedDepartureTime");
        }
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public void setStatus(FlightStatus status) {
        if (status == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".status cannot be empty");
        }
        this.status = status;
    }

    public void setAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".aircraft cannot be empty");
        }
        this.aircraft = aircraft;
    }
}
