package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FixedWingAircraft extends Aircraft {

    @Getter
    private Double wingLength;
    @Getter
    private int landingWheels;

    public String getType() {
        return "fixed_wing";
    }

    public FixedWingAircraft(String model, int capacity, Double wingLength, int landingWheels, AircraftRole role) {
        super(model, capacity, role);
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
