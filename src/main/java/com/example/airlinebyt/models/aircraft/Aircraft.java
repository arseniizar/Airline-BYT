package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class Aircraft implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    private String model;
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Setter
    private AircraftType type;

    @Setter
    private Double cargoCapacity;

    @Setter
    private String personalizedInterior;

    public Aircraft(String model, int capacity, AircraftType type) {
        setModel(model);
        setCapacity(capacity);
        this.type = type;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Aircraft model cannot be empty.");
        }
        this.model = model;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Aircraft capacity must be positive.");
        }
        this.capacity = capacity;
    }
}
