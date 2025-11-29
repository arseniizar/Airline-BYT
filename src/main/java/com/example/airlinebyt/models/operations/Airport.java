package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.embeddable.Location;
import jakarta.persistence.*;
import lombok.Getter;


public class Airport implements BaseEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String iataCode;
    @Getter
    private String name;

    @Getter
    @Embedded
    private Location location;


    public Airport(String iataCode, String name, Location location) {
        setIataCode(iataCode);
        setName(name);
        setLocation(location);
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

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
