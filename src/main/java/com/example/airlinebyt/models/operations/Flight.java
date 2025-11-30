package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.enums.FlightStatus;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.person.CrewMember;
import com.example.airlinebyt.models.person.Pilot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Flight implements BaseEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private String flightNumber;

    @Getter
    private LocalDateTime expectedDepartureTime;

    @Getter
    private LocalDateTime expectedArrivalTime;

    @Getter
    @Setter
    private LocalDateTime actualDepartureTime;

    @Getter
    @Setter
    private LocalDateTime actualArrivalTime;

    @Getter
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Getter
    @ManyToMany
    @JoinTable(name = "flight_pilots")
    private Set<Pilot> pilots = new HashSet<>();

    @Getter
    @ManyToMany
    @JoinTable(name = "flight_crew")
    private Set<CrewMember> crew = new HashSet<>();


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

    public void setOrigin(Airport origin) {
        if (origin == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".origin cannot be empty");
        }

        this.origin = origin;
    }

    public void setDestination(Airport destination) {
        if (destination == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".destination cannot be empty");
        }

        this.destination = destination;
    }

    public void setAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".aircraft cannot be empty");
        }

        this.aircraft = aircraft;
    }
}
