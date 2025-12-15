package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.person.Employee;
import com.example.airlinebyt.models.person.Mechanic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    static class EmployeeImpl extends Employee {
        public EmployeeImpl(String firstName, String lastName, LocalDate birthDate, LocalDate hireDate, String education) {
            super(firstName, lastName, birthDate, hireDate, education, null);
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

    private Employee supervisor;
    private Employee subordinate;

    @BeforeEach
    void setUpForAssociationTests() {
        supervisor = new Mechanic("Senior", "Mechanic", LocalDate.of(1980, 1, 1), LocalDate.of(2000, 1, 1), "Engineering", 90000.0);
        subordinate = new Mechanic("Junior", "Mechanic", LocalDate.of(1995, 1, 1), LocalDate.of(2020, 1, 1), "Technical School", 50000.0);
    }

    @Test
    void setSupervisor_shouldEstablishLink() {
        setUpForAssociationTests();
        subordinate.setSupervisor(supervisor);
        assertSame(supervisor, subordinate.getSupervisor());
    }

    @Test
    void setSupervisor_shouldThrowExceptionForSelfSupervision() {
        setUpForAssociationTests();
        assertThrows(IllegalArgumentException.class,
                () -> supervisor.setSupervisor(supervisor)
        );
    }
}
