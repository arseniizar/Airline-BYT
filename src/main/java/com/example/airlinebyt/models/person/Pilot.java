package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("PILOT")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Pilot extends Employee implements BaseEntity {

    private String licenceNumber;
    private LocalDate licenceWarranty;
    private Double baseSalary;

    @ManyToMany(mappedBy = "pilots")
    private Set<Flight> flights = new HashSet<>();
}
