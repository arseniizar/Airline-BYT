package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.operations.Flight;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("CREW_MEMBER")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CrewMember extends Employee {

    private Double baseSalary;
    private String position;

    @ManyToMany(mappedBy = "crew")
    private Set<Flight> flights = new HashSet<>();
}
