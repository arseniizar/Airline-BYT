package com.example.airlinebyt.models.aircraft;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Private extends Aircraft {
    private String personalizedInterior;
    private Double annualOperatingCost;

}
