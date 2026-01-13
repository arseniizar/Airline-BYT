package com.example.airlinebyt.models.person;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
public abstract class Employee implements PersonType {

    @Getter
    private LocalDate hireDate;

    @Getter
    private String education;

    @Getter
    private Person person;


    // 4. Reflexive Association
    @Getter
    private Employee supervisor;

    public Employee(Person person, LocalDate hireDate, String education, Employee supervisor) {
        setPerson(person);
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

    private void setPerson(Person person) {
        this.person = person;
        person.addType(this);
    }

}
