package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RotorcraftAircraft extends Aircraft {

    private int amountOfRotors;
    private Double maxHoverAltitude;

    public RotorcraftAircraft(String model, int capacity, AircraftType type, int amountOfRotors, Double maxHoverAltitude) {
        super(model, capacity, type);
        setAmountOfRotors(amountOfRotors);
        setMaxHoverAltitude(maxHoverAltitude);
    }

    public void setAmountOfRotors(int amountOfRotors) {
        if (amountOfRotors <= 0) {
            throw new IllegalArgumentException("Amount of rotors must be a positive number.");
        }
        this.amountOfRotors = amountOfRotors;
    }

    public void setMaxHoverAltitude(Double maxHoverAltitude) {
        if (maxHoverAltitude == null || maxHoverAltitude < 0) {
            throw new IllegalArgumentException("Max hover altitude cannot be null or negative.");
        }
        this.maxHoverAltitude = maxHoverAltitude;
    }
}
