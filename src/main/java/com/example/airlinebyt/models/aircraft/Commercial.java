package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("COMMERCIAL")
@Getter
@NoArgsConstructor
public class Commercial extends Aircraft {

    public Commercial(String model, int capacity, Double cargoCapacity) {
        super(model, capacity, AircraftType.COMMERCIAL);
        setCargoCapacity(cargoCapacity);
    }
}
