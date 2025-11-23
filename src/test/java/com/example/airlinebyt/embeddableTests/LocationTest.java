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

        Set<ConstraintViolation<Location>> violations = validator.validate(loc);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidLocation_BlankCountry() {
        Location loc = new Location("", "Warsaw");

        Set<ConstraintViolation<Location>> violations = validator.validate(loc);
        assertFalse(violations.isEmpty());
    }
}
