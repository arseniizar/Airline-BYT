package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Seat implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @Transient
    private Aircraft aircraft;

    public Seat(String seatNumber, SeatClass seatClass, Aircraft aircraft) {
        setSeatNumber(seatNumber);
        setSeatClass(seatClass);
        setAircraft(aircraft);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatNumber(String seatNumber) {
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be empty.");
        }
        this.seatNumber = seatNumber;
    }

    public void setSeatClass(SeatClass seatClass) {
        if (seatClass == null) {
            throw new IllegalArgumentException("Seat class cannot be null.");
        }
        this.seatClass = seatClass;
    }

    public void setAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft cannot be null.");
        }
        this.aircraft = aircraft;
    }
}
