package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.embeddable.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;

@NoArgsConstructor
public class Airport implements BaseEntity {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Getter private String iataCode;
    @Getter private String name;
    @Getter @Embedded private Location location;
    private Map<String, Flight> departingFlights = new HashMap<>();

    public Airport(String iataCode, String name, Location location) {
        setIataCode(iataCode);
        setName(name);
        setLocation(location);
    }

    // --- ASSOCIATION MANAGEMENT: Qualified ---
    public Optional<Flight> findDepartingFlightByNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("Flight number for search cannot be empty.");
        }
        return Optional.ofNullable(departingFlights.get(flightNumber));
    }

    public void addDeparture(Flight flight) {
        if (flight == null || flight.getFlightNumber() == null) throw new IllegalArgumentException("Flight and its number are required.");
        if (!departingFlights.containsKey(flight.getFlightNumber())) {
            departingFlights.put(flight.getFlightNumber(), flight);
            if (flight.getOrigin() != this) {
                flight.setOrigin(this);
            }
        }
    }

    public void removeDeparture(Flight flight) {
        if (flight != null && departingFlights.containsKey(flight.getFlightNumber())) {
            departingFlights.remove(flight.getFlightNumber());
            // If the flight still points to this airport as origin, we should probably
            // notify it, but the main logic is in Flight.setOrigin(null)
        }
    }

    public Map<String, Flight> getDepartingFlights() {
        return Collections.unmodifiableMap(departingFlights);
    }

    // --- Standard class methods ---
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setIataCode(String iataCode) {
        if (iataCode == null || iataCode.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".iataCode cannot be empty");
        }
        if (iataCode.length() != 3) {
            throw new IllegalArgumentException(this.getClass().getName() + ".iataCode length must be 3 for airports");
        }
        this.iataCode = iataCode;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".name cannot be empty");
        }
        this.name = name;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".location cannot be empty");
        }
        this.location = location;
    }
}
