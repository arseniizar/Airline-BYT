package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class CrewMember extends Employee implements BaseEntity {
    @Getter
    private Double baseSalary;

    @Getter
    private String position;

    @Getter
    @ManyToMany(mappedBy = "crew")
    private final Set<Flight> flights = new HashSet<>();

    public CrewMember(String firstName, String lastName, java.time.LocalDate birthDate,
                      java.time.LocalDate hireDate, String education,
                      Double baseSalary, String position) {
        super(firstName, lastName, birthDate, hireDate, education);
        setBaseSalary(baseSalary);
        setPosition(position);
    }

    public void setBaseSalary(Double baseSalary) {
        if (baseSalary == null || baseSalary < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".baseSalary cannot be null or negative");
        }
        this.baseSalary = baseSalary;
    }

    public void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".position cannot be empty");
        }
        this.position = position;
    }
}
