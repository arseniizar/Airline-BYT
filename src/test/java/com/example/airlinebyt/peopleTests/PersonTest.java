package com.example.airlinebyt.peopleTests;


import com.example.airlinebyt.models.person.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    static class PersonImpl extends Person {}

    @Test
    void testBasicFields() {
        PersonImpl p = new PersonImpl();

        p.setFirstName("Olek");
        p.setLastName("XYZ");
        p.setBirthDate(LocalDate.of(2003, 5, 10));

        assertEquals("Olek", p.getFirstName());
        assertEquals("XYZ", p.getLastName());
        assertEquals(LocalDate.of(2003, 5, 10), p.getBirthDate());
    }

    @Test
    void testAgeCalculation() {
        PersonImpl p = new PersonImpl();
        p.setBirthDate(LocalDate.now().minusYears(30));

        assertEquals(30, p.getAge());
    }

    @Test
    void testAgeWhenBirthDateNull() {
        PersonImpl p = new PersonImpl();
        assertNull(p.getAge());
    }
}

