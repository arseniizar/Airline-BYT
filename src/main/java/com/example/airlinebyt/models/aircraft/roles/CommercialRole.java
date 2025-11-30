package com.example.airlinebyt.models.aircraft.roles;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommercialRole extends AircraftRole {

    private double cargoCapacity;

    public CommercialRole() {
    }

    public CommercialRole(double cargoCapacity) {
        this.setCargoCapacity(cargoCapacity);
    }

    public void setCargoCapacity(double cargoCapacity) {
        if (cargoCapacity < 0) {
            throw new IllegalArgumentException("Cargo capacity cannot be negative. Provided value: " + cargoCapacity);
        }
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "CommercialRole{" +
                "cargoCapacity=" + cargoCapacity +
                '}';
    }
}
