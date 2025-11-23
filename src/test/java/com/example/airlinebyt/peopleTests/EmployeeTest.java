package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.person.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    static class EmployeeImpl extends Employee {}

    @Test
    void testHireDateAndEducation() {
        EmployeeImpl e = new EmployeeImpl();

        e.setHireDate(LocalDate.of(2024, 3, 15));
        e.setEducation("Bachelor Degree");

        assertEquals(LocalDate.of(2024, 3, 15), e.getHireDate());
        assertEquals("Bachelor Degree", e.getEducation());
    }

    @Test
    void testYearsOfExperience() {
        EmployeeImpl e = new EmployeeImpl();

        e.setHireDate(LocalDate.now().minusYears(3));

        assertEquals(3, e.getYearsOfExperience());
    }

    @Test
    void testYearsOfExperienceWhenNull() {
        EmployeeImpl e = new EmployeeImpl();
        e.setHireDate(null);

        assertNull(e.getYearsOfExperience());
    }
}
