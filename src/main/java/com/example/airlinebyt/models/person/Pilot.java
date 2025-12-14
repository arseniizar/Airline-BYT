package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.operations.Flight;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class Pilot extends Employee {
    @Getter private String licenceNumber;
    @Getter private LocalDate licenceWarranty;
    @Getter private Double baseSalary;
    private Set<Flight> flights = new HashSet<>();

    public Pilot(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate, String education, String licenceNumber, LocalDate licenceWarranty, Double baseSalary) {
        super(firstName, lastName, birthDate, hireDate, education);
        setLicenceNumber(licenceNumber);
        setLicenceWarranty(licenceWarranty);
        setBaseSalary(baseSalary);
    }

    // --- ASSOCIATION MANAGEMENT ---
    public void addFlight(Flight flight) {
        if (flight == null) throw new IllegalArgumentException("Flight cannot be null.");
        if (!this.flights.contains(flight)) {
            this.flights.add(flight);
        }
    }

    public void removeFlight(Flight flight) {
        if (flight != null && this.flights.contains(flight)) {
            this.flights.remove(flight);
            if(flight.getPilots().contains(this)) {
                flight.unassignPilot(this);
            }
        }
    }

    public Set<Flight> getFlights() {
        return Collections.unmodifiableSet(flights);
    }

    // --- Standard class methods ---
    public void setLicenceNumber(String licenceNumber) {
        if (licenceNumber == null || licenceNumber.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceNumber cannot be empty");
        }
        this.licenceNumber = licenceNumber;
    }

    public void setLicenceWarranty(LocalDate licenceWarranty) {
        if (licenceWarranty == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceWarranty cannot be empty");
        }
        if (licenceWarranty.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceWarranty cannot be in the past");
        }
        this.licenceWarranty = licenceWarranty;
    }

    public void setBaseSalary(Double baseSalary) {
        if (baseSalary == null || baseSalary < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".baseSalary cannot be null or negative");
        }
        this.baseSalary = baseSalary;
    }
}
