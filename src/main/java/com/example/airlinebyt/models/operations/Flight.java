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
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private String flightNumber;

    @Getter private LocalDateTime expectedDepartureTime;
    @Getter private LocalDateTime expectedArrivalTime;
    @Getter @Setter private LocalDateTime actualDepartureTime;
    @Getter @Setter private LocalDateTime actualArrivalTime;
    @Getter private FlightStatus status;
    @Getter @ManyToOne @JoinColumn(name = "aircraft_id") private Aircraft aircraft;

    // --- ASSOCIATION: Qualified (with Airport) ---
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    // --- ASSOCIATION: Aggregation (with CrewMember) ---
    @ManyToMany
    @JoinTable(name = "flight_crew")
    private Set<CrewMember> crew = new HashSet<>();

    // --- ASSOCIATION: With Attribute (Pilot + Role) ---
    @Transient
    private Map<Pilot, String> pilotRoles = new HashMap<>();

    // --- ASSOCIATION: Reflexive ---
    @Getter private Flight previousLeg;
    @Getter private Flight nextLeg;


    // --- ASSOCIATION MANAGEMENT: Aggregation (CrewMember) ---
    public void addCrewMember(CrewMember member) {
        if (member == null) {
            throw new IllegalArgumentException("Crew member cannot be null.");
        }
        if (this.crew.contains(member)) {
            return;
        }
        this.crew.add(member);
        member.assignToFlightInternal(this); // Reverse connection
    }

    public void removeCrewMember(CrewMember member) {
        if (member != null && this.crew.contains(member)) {
            this.crew.remove(member);
            member.removeFromFlightInternal(this); // Reverse connection
        }
    }

    public Set<CrewMember> getCrew() {
        return Collections.unmodifiableSet(crew);
    }

    // --- ASSOCIATION MANAGEMENT: With Attribute (Pilot + Role) ---
    public void assignPilot(Pilot pilot, String role) {
        if (pilot == null || role == null || role.isBlank()) {
            throw new IllegalArgumentException("Pilot and role must be provided.");
        }
        if (this.pilotRoles.containsKey(pilot)) {
            throw new IllegalStateException("This pilot is already assigned to this flight.");
        }
        this.pilotRoles.put(pilot, role);
        pilot.addFlightInternal(this); // Reverse connection
    }

    public void unassignPilot(Pilot pilot) {
        if (pilot != null && this.pilotRoles.containsKey(pilot)) {
            this.pilotRoles.remove(pilot);
            pilot.removeFlightInternal(this); // Reverse connection
        }
    }

    public Map<Pilot, String> getPilotRoles() {
        return Collections.unmodifiableMap(pilotRoles);
    }

    public Set<Pilot> getPilots() {
        return Collections.unmodifiableSet(pilotRoles.keySet());
    }

    // --- ASSOCIATION MANAGEMENT: Reflexive ---
    public void setNextLeg(Flight next) {
        if (next == this) {
            throw new IllegalArgumentException("A flight cannot be its own next leg.");
        }
        if (this.nextLeg != null) {
            this.nextLeg.previousLeg = null;
        }
        this.nextLeg = next;
        if (next != null && next.previousLeg != this) {
            next.setPreviousLegInternal(this); // Reverse connection
        }
    }

    private void setPreviousLegInternal(Flight prev) {
        this.previousLeg = prev;
    }

    // --- ASSOCIATION MANAGEMENT: Qualified (with Airport) ---
    public void setOrigin(Airport origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin airport cannot be null.");
        }
        if (this.origin != null && !this.origin.equals(origin)) {
            this.origin.removeDepartureInternal(this);
        }
        this.origin = origin;
        origin.addDepartureInternal(this); // Reverse connection
    }

    public void setDestination(Airport destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination airport cannot be null.");
        }
        this.destination = destination;
    }

    // --- Standard class methods ---
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
