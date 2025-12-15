package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.operations.Flight;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class CrewMember extends Employee {
    @Getter private Double baseSalary;
    @Getter private String position;
//    @JsonBackReference("flight-crew")
    @JsonIgnore
    private Set<Flight> flights = new HashSet<>();
    public static final int MAX_FLIGHTS_PER_DAY = 3;

    public CrewMember(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate, String education, Double baseSalary, String position) {
        super(firstName, lastName, birthDate, hireDate, education, null);
        setBaseSalary(baseSalary);
        setPosition(position);
    }

    public String getType() {
        return "crew_member";
    }

    // --- ASSOCIATION MANAGEMENT ---
    public void assignToFlight(Flight flight) {
        if (flight == null) throw new IllegalArgumentException("Flight cannot be null.");
        if (!this.flights.contains(flight)) {
            this.flights.add(flight);
            flight.addCrewMember(this);
        }
    }

    public void removeFromFlight(Flight flight) {
        if (flight != null && this.flights.contains(flight)) {
            this.flights.remove(flight);
            flight.removeCrewMember(this);
        }
    }

    public Set<Flight> getFlights() {
        return Collections.unmodifiableSet(flights);
    }

    // --- Standard class methods ---
    public void setBaseSalary(Double baseSalary) {
        if (baseSalary == null || baseSalary < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".baseSalary cannot be null or negative");
        }
        this.baseSalary = baseSalary;
    }

    public void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".position cannot be empty");
        }
        this.position = position;
    }
}
