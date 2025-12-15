package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RotorcraftAircraft extends Aircraft {

    @Getter
    private int amountOfRotors;
    @Getter
    private Double maxHoverAltitude;

    public String getType() {
        return "rotorcraft";
    }

    public RotorcraftAircraft(String model, int capacity, int amountOfRotors, Double maxHoverAltitude, AircraftRole role) {
        super(model, capacity, role);
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