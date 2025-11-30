package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FixedWingAircraft extends Aircraft {

    private Double wingLength;
    private int landingWheels;

    public FixedWingAircraft(String model, int capacity, AircraftType type, Double wingLength, int landingWheels) {
        super(model, capacity, type);
        setWingLength(wingLength);
        setLandingWheels(landingWheels);
    }

    public void setWingLength(Double wingLength) {
        if (wingLength == null || wingLength <= 0) {
            throw new IllegalArgumentException("Wing length must be a positive number.");
        }
        this.wingLength = wingLength;
    }

    public void setLandingWheels(int landingWheels) {
        if (landingWheels <= 0) {
            throw new IllegalArgumentException("Number of landing wheels must be a positive number.");
        }
        this.landingWheels = landingWheels;
    }
}
