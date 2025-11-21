package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@DiscriminatorValue("EMPLOYEE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class Employee extends Person implements BaseEntity {

    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    private String education;

    @Transient
    public Integer getYearsOfExperience() {
        if (this.hireDate == null) {
            return null;
        }
        return Period.between(this.hireDate, LocalDate.now()).getYears();
    }
}
