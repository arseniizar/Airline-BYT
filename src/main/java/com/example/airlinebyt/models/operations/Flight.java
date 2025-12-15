package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.booking.Ticket;
import com.example.airlinebyt.models.person.CrewMember;
import com.example.airlinebyt.models.person.Pilot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Getter
    @Setter
    private Long id;
    @Getter
    private String flightNumber;
    @Getter
    private LocalDateTime expectedDepartureTime;
    @Getter
    private LocalDateTime expectedArrivalTime;
    @Getter
    private FlightStatus status;
    @Getter
    private Aircraft aircraft;
    @JsonManagedReference("flight-origin")
    @Getter
    private OriginMetadata originMetadata;
    @JsonManagedReference("flight-destination")
    @Getter
    private DestinationMetadata destinationMetadata;
//    @JsonManagedReference("flight-crew")
    private Set<CrewMember> crew = new HashSet<>();
    @JsonIgnore
    private Map<Pilot, String> pilotRoles = new HashMap<>();
    // 5. Qualified association
    private Map<String, Ticket> tickets = new HashMap<>();
    @Getter
    private Flight previousLeg;
    @Getter
    private Flight nextLeg;
    public static final int MAX_FLIGHT_DURATION_HOURS = 18;


    public Flight(String flightNumber, LocalDateTime expectedDepartureTime, LocalDateTime expectedArrivalTime, FlightStatus status, Aircraft aircraft, Airport origin, Airport destination) {
        setFlightNumber(flightNumber);
        setExpectedDepartureTime(expectedDepartureTime);
        setExpectedArrivalTime(expectedArrivalTime);
        setStatus(status);
        setAircraft(aircraft);
        setOrigin(origin);
        setDestination(destination);
    }

    public void addTicket(Ticket ticket) {
        if (ticket == null || ticket.getTicketNumber() == null || ticket.getTicketNumber().isBlank()) {
            throw new IllegalArgumentException("Ticket and its number are required.");
        }
        if (!this.tickets.containsKey(ticket.getTicketNumber())) {
            this.tickets.put(ticket.getTicketNumber(), ticket);
            ticket.setFlight(this);
        }
    }

    public void removeTicket(Ticket ticket) {
        if (ticket != null && this.tickets.containsKey(ticket.getTicketNumber())) {
            this.tickets.remove(ticket.getTicketNumber());
            ticket.setFlight(null);
        }
    }

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

    public OriginMetadata setOrigin(Airport origin) {
        if (origin == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".origin cannot be empty");
        }
        this.originMetadata = new OriginMetadata(this, origin);
        return this.originMetadata;
    }

    public DestinationMetadata setDestination(Airport destination) {
        if (destination == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".destination cannot be empty");
        }
        this.destinationMetadata = new DestinationMetadata(this, destination);
        return this.destinationMetadata;
    }


    public void assignPilot(Pilot pilot, String role) {
        if (pilot == null || role == null || role.isBlank())
            throw new IllegalArgumentException("Pilot and role are required.");
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
    public Set<CrewMember> getCrew() {
        return Collections.unmodifiableSet(crew);
    }

    public Map<Pilot, String> getPilotRoles() {
        return Collections.unmodifiableMap(pilotRoles);
    }

    public Set<Pilot> getPilots() {
        return Collections.unmodifiableSet(pilotRoles.keySet());
    }


    public void setFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".flightNumber cannot be empty");
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
