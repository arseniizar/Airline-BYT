package com.example.airlinebyt.services.factories;

import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import org.springframework.stereotype.Component;

@Component
public class AircraftFactory {

    public AircraftFactory() {
    }

    public FixedWingAircraft createFixedWingAircraft(String model, int capacity, Double wingLength, int landingWheels, AircraftRole role) {
        return new FixedWingAircraft(model, capacity, wingLength, landingWheels, role);
    }

    public RotorcraftAircraft createRotorcraftAircraft(String model, int capacity, int amountOfRotors, Double maxHoverAltitude, AircraftRole role) {
        return new RotorcraftAircraft(model, capacity, amountOfRotors, maxHoverAltitude, role);
    }
}
