package com.example.airlinebyt.aircraftTests;

import com.example.airlinebyt.enums.ConstructionType;
import com.example.airlinebyt.enums.RoleType;
import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.models.booking.Seat;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {

    @Test
    void shouldCreateCommercialFixedWing_AndAllowValidAttributes() {
        // 1. Створення літака (Fixed Wing + Commercial)
        Aircraft plane = new Aircraft("Boeing 737", 189, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        // 2. Встановлення специфічних атрибутів (має пройти успішно)
        plane.setFixedWingAttributes(34.3, 6);
        plane.setCommercialAttributes(4500.0);

        // 3. Перевірка значень
        assertEquals("Boeing 737", plane.getModel());
        assertEquals(34.3, plane.getWingLength());
        assertEquals(6, plane.getLandingWheels());
        assertEquals(4500.0, plane.getCargoCapacity());

        // Перевірка типів
        assertEquals(ConstructionType.FIXED_WING, plane.getConstructionType());
        assertEquals(RoleType.COMMERCIAL, plane.getRoleType());
    }

    @Test
    void shouldCreatePrivateRotorcraft_AndAllowValidAttributes() {
        // 1. Створення гвинтокрила (Rotorcraft + Private)
        Aircraft heli = new Aircraft("Bell 407", 6, ConstructionType.ROTORCRAFT, RoleType.PRIVATE);

        // 2. Встановлення специфічних атрибутів
        heli.setRotorcraftAttributes(4, 3500.0);
        heli.setPrivateAttributes("VIP Interior");

        // 3. Перевірка значень
        assertEquals(4, heli.getAmountOfRotors());
        assertEquals(3500.0, heli.getMaxHoverAltitude());
        assertEquals("VIP Interior", heli.getPersonalizedInterior());
    }

    @Test
    void shouldThrowException_WhenAccessingWingLength_OnRotorcraft() {
        // Створюємо Вертоліт
        Aircraft heli = new Aircraft("Heli", 4, ConstructionType.ROTORCRAFT, RoleType.PRIVATE);

        // Спроба отримати довжину крила має викликати помилку (Guard Logic)
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            heli.getWingLength();
        });

        assertTrue(exception.getMessage().contains("Operation not allowed"));
    }

    @Test
    void shouldThrowException_WhenSettingRotors_OnFixedWing() {
        // Створюємо Літак
        Aircraft plane = new Aircraft("Plane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        // Спроба встановити ротори має викликати помилку
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            plane.setRotorcraftAttributes(4, 1000.0);
        });

        assertTrue(exception.getMessage().contains("Operation not allowed"));
    }

    @Test
    void shouldThrowException_WhenMixingRoles() {
        // Створюємо Комерційний літак
        Aircraft plane = new Aircraft("Plane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        // Спроба задати приватний інтер'єр
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            plane.setPrivateAttributes("Gold");
        });

        assertTrue(exception.getMessage().contains("Operation not allowed"));
    }

    @Test
    void shouldAddSeatCorrectly() {
        Aircraft plane = new Aircraft("Plane", 100, ConstructionType.FIXED_WING, RoleType.COMMERCIAL);

        plane.addSeat("1A", SeatClass.BUSINESS);
        Optional<Seat> seat = plane.getSeat("1A");

        assertTrue(seat.isPresent());
        assertEquals(SeatClass.BUSINESS, seat.get().getSeatClass());
    }

    @Test
    void constructor_ShouldRequireTypes() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aircraft("Model", 100, null, RoleType.COMMERCIAL);
        });
    }
}
