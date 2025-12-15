package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.embeddable.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class Airport implements BaseEntity {
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Getter private String iataCode;
    @Getter private String name;
    @Getter @Embedded private Location location;
    // 1. Basic association
    private final Set<Aircraft> _basedAircraft = new HashSet<>();
    // 6. Association with attribute
    private List<OriginMetadata> _origins = new ArrayList<>();
    private List<DestinationMetadata> _destinations = new ArrayList<>();


    public Airport(String iataCode, String name, Location location) {
        setIataCode(iataCode);
        setName(name);
        setLocation(location);
    }

    public void AddBasedAircraft(Aircraft aircraft) {
        if (aircraft == null) throw new IllegalArgumentException("Aircraft cannot be null.");
        if (!_basedAircraft.contains(aircraft)) {
            _basedAircraft.add(aircraft);
            aircraft.setBaseAirport(this);
        }
    }

    public void RemoveBasedAircraft(Aircraft aircraft) {
        if (aircraft != null) {
            _basedAircraft.remove(aircraft);
            if (aircraft.getBaseAirport() == this) {
                aircraft.setBaseAirport(null);
            }
        }
    }

    public Set<Aircraft> getBasedAircraft() {
        return Collections.unmodifiableSet(_basedAircraft);
    }

    public void addFlightOrigin(Flight flight) {
        if (flight == null) throw new IllegalArgumentException("Flight cannot be null.");
        OriginMetadata originMetadata = flight.setOrigin(this);
        _origins.add(originMetadata);
    }

    public void addFlightDestination(Flight flight) {
        if (flight == null) throw new IllegalArgumentException("Flight cannot be null.");
        DestinationMetadata destinationMetadata = flight.setDestination(this);
        _destinations.add(destinationMetadata);
    }

    public List<OriginMetadata> getOrigins() {
        return Collections.unmodifiableList(_origins);
    }

    public List<DestinationMetadata> getDestinations() {
        return Collections.unmodifiableList(_destinations);
    }

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
