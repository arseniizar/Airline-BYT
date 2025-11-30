package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.AircraftType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Private extends Aircraft {
    private String personalizedInterior;
    private Double annualOperatingCost;

    public Private(String model, int capacity, String personalizedInterior, Double annualOperatingCost) {
        super(model, capacity, AircraftType.PRIVATE);
        setPersonalizedInterior(personalizedInterior);
        setAnnualOperatingCost(annualOperatingCost);
    }

    @Override
    public void setPersonalizedInterior(String personalizedInterior) {
        if (personalizedInterior == null || personalizedInterior.trim().isEmpty()) {
            throw new IllegalArgumentException("Personalized interior description cannot be empty for a private aircraft.");
        }
        this.personalizedInterior = personalizedInterior;
    }

    public void setAnnualOperatingCost(Double annualOperatingCost) {
        if (annualOperatingCost == null || annualOperatingCost < 0) {
            throw new IllegalArgumentException("Annual operating cost cannot be null or negative.");
        }
        this.annualOperatingCost = annualOperatingCost;
    }
}
