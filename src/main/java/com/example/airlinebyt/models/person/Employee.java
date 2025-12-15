package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.Transient;
import lombok.Getter;


import java.time.LocalDate;
import java.time.Period;


public abstract class Employee extends Person implements BaseEntity {

    @Getter
    private LocalDate hireDate;

    @Getter
    private String education;

    // 4. Reflexive Association
    @Getter
    private Employee supervisor;

    public Employee(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate, String education, Employee supervisor) {
        super(firstName, lastName, birthDate);
        setHireDate(hireDate);
        setEducation(education);
        setSupervisor(supervisor);
    }

    @Transient
    public Integer getYearsOfExperience() {
        if (this.hireDate == null) {
            return null;
        }
        return Period.between(this.hireDate, LocalDate.now()).getYears();
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".hireDate cannot be empty");
        }

        if (hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(this.getClass().getName() + ".hireDate cannot be in the future");
        }

        this.hireDate = hireDate;
    }

    public void setEducation(String education) {
        if (education == null || education.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".education cannot be empty");
        }
        this.education = education;
    }

    public void setSupervisor(Employee supervisor) {
        if (supervisor == this) {
            throw new IllegalArgumentException("An employee cannot supervise themselves.");
        }
        this.supervisor = supervisor;
    }

    public void setId(Long id) {
        super.setId(id);
    }
}
