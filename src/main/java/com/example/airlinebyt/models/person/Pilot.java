package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Pilot extends Employee implements BaseEntity {
    @Getter
    private String licenceNumber;

    @Getter
    private LocalDate licenceWarranty;

    @Getter
    private Double baseSalary;

    @Getter
    @ManyToMany(mappedBy = "pilots")
    private Set<Flight> flights = new HashSet<>();

    public Pilot(String firstName, String lastName, LocalDate birthDate,
                 LocalDate hireDate, String education,
                 String licenceNumber, LocalDate licenceWarranty,
                 Double baseSalary) {
        super(firstName, lastName, birthDate, hireDate, education);
        setLicenceNumber(licenceNumber);
        setLicenceWarranty(licenceWarranty);
        setBaseSalary(baseSalary);
    }

    public void setLicenceNumber(String licenceNumber) {
        if (licenceNumber == null || licenceNumber.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceNumber cannot be empty");
        }

        // TODO: Додати перевірку формату номера ліцензії, якщо є конкретні вимоги

        this.licenceNumber = licenceNumber;
    }

    public void setLicenceWarranty(LocalDate licenceWarranty) {
        if (licenceWarranty == null) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceWarranty cannot be empty");
        }

        if (licenceWarranty.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(this.getClass().getName() + ".licenceWarranty cannot be in the past");
        }

        this.licenceWarranty = licenceWarranty;
    }

    public void setBaseSalary(Double baseSalary) {
        if (baseSalary == null || baseSalary < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".baseSalary cannot be null or negative");
        }
        this.baseSalary = baseSalary;
    }
}
