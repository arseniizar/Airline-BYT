package com.example.airlinebyt.embeddableTests;
import com.example.airlinebyt.BaseModelTest;
import com.example.airlinebyt.models.embeddable.Location;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest extends BaseModelTest {

    @Test
    void testValidLocation() {
        Location loc = new Location("Poland", "Warsaw");

        // ??? Що ми тут взагалі перевіряємо?
        Set<ConstraintViolation<Location>> violations = validator.validate(loc);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidLocation_BlankCountry() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Location("", "Warsaw");
        });
    }
}
