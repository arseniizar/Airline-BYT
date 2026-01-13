package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.operations.Backlog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Mechanic extends Employee {
    private Double baseSalary;
    private List<Backlog> assignedBacklogs = new ArrayList<>();

    public String getType() {
        return "mechanic";
    }

    public Mechanic(Person person, LocalDate hireDate, String education, Employee supervisor,
                    Double baseSalary) {
        super(person, hireDate, education, supervisor);
        setBaseSalary(baseSalary);
    }


    public void setBaseSalary(Double baseSalary) {
        if (baseSalary == null || baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be null or negative.");
        }
        this.baseSalary = baseSalary;
    }

    public void assignBacklog(Backlog backlog) {
        if (backlog == null) {
            throw new IllegalArgumentException("Cannot assign a null backlog.");
        }
        this.assignedBacklogs.add(backlog);
    }
}
