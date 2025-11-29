package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.person.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    static class EmployeeImpl extends Employee {
        public EmployeeImpl(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate, String education) {
            super(firstName, lastName, birthDate, hireDate, education);
        }
    }

    @Test
    void testHireDateAndEducation() {
        EmployeeImpl e = new EmployeeImpl(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2024, 3, 15),
                "Bachelor's Degree"
        );


        assertEquals(LocalDate.of(2024, 3, 15), e.getHireDate());
        assertEquals("Bachelor's Degree", e.getEducation());
    }

    @Test
    void testYearsOfExperience() {
        EmployeeImpl e = new EmployeeImpl(
                "Jane",
                "Smith",
                LocalDate.of(1985, 5, 20),
                LocalDate.now().minusYears(3),
                "Master's Degree"
        );

        assertEquals(3, e.getYearsOfExperience());
    }

    @Test
    void testYearsOfExperienceWhenNull() {
        EmployeeImpl e = new EmployeeImpl(
                "Alice",
                "Johnson",
                LocalDate.of(1992, 7, 15),
                LocalDate.now().minusYears(5),
                "PhD"
        );

        assertThrowsExactly(IllegalArgumentException.class, () -> e.setHireDate(null));
    }
}
