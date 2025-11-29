package com.example.airlinebyt.models.aircraft;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("COMMERCIAL")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Commercial extends Aircraft {

}
