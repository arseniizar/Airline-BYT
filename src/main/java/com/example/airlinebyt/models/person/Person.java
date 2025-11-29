package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

// I deleted the annotations because they obfuscate the code for now, and implement default getters and setters incorrectly with Lombok
public abstract class Person implements BaseEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private LocalDate birthDate;

    protected Person() {
    }

    // Похідний атрибут /age реалізується методом, а не полем в БД
    @Transient // Не зберігати це поле в базі даних
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Person(String firstName, String lastName, LocalDate birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".firstName cannot be empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".lastName cannot be empty");
        }
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".birthDate cannot be empty");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(this.getClass().getName() + ".birthDate cannot be in the future");
        }

        this.birthDate = birthDate;
    }
}
