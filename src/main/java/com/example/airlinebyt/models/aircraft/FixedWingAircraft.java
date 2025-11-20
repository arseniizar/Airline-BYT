package com.example.airlinebyt.models.aircraft;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FixedWingAircraft extends Aircraft {

    private Double wingLength;
    private int landingWheels;
}