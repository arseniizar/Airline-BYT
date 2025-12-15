package com.example.airlinebyt.peopleTests;

import com.example.airlinebyt.models.operations.Backlog;
import com.example.airlinebyt.models.person.Mechanic;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MechanicTest {

    @Test
    void shouldCreateMechanicWithValidData() {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate hire = LocalDate.of(2023, 1, 10);

        Mechanic mechanic = new Mechanic(
                "John",
                "Doe",
                birth,
                hire,
                "Technical School",
                5000.0
        );

        assertEquals("John", mechanic.getFirstName());
        assertEquals("Doe", mechanic.getLastName());
        assertEquals(birth, mechanic.getBirthDate());
        assertEquals(hire, mechanic.getHireDate());
        assertEquals("Technical School", mechanic.getEducation());
        assertEquals(5000.0, mechanic.getBaseSalary());
        assertEquals("mechanic", mechanic.getType());
    }

    @Test
    void shouldThrowExceptionForNullSalary() {
        LocalDate birth = LocalDate.of(1990, 5, 20);
        LocalDate hire = LocalDate.of(2023, 1, 10);

        assertThrows(IllegalArgumentException.class, () ->
                new Mechanic("John", "Doe", birth, hire, "Tech", null)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeSalary() {
        LocalDate birth = LocalDate.of(1985, 7, 14);
        LocalDate hire = LocalDate.of(2015, 3, 1);

        assertThrows(IllegalArgumentException.class, () ->
                new Mechanic("name1", "last1", birth, hire, "Technical", -2000.0)
        );
    }

    @Test
    void shouldAssignBacklog() {
        Mechanic mechanic = new Mechanic();
        mechanic.setBaseSalary(3000.0);

        Backlog backlog = new Backlog();
        mechanic.assignBacklog(backlog);

        assertEquals(1, mechanic.getAssignedBacklogs().size());
        assertSame(backlog, mechanic.getAssignedBacklogs().get(0));
    }

    @Test
    void shouldThrowExceptionWhenAssigningNullBacklog() {
        Mechanic mechanic = new Mechanic();
        mechanic.setBaseSalary(3000.0);

        assertThrows(IllegalArgumentException.class,
                () -> mechanic.assignBacklog(null));
    }

    @Test
    void defaultConstructorShouldInitializeList() {
        Mechanic mechanic = new Mechanic();

        assertNotNull(mechanic.getAssignedBacklogs());
        assertTrue(mechanic.getAssignedBacklogs().isEmpty());
    }
}

