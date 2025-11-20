package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private AircraftType type;

    private Double cargoCapacity;
    private String personalizedInterior;
}
