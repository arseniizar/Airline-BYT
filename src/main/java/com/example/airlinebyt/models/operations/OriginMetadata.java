package com.example.airlinebyt.models.operations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import java.time.LocalDateTime;

public class OriginMetadata {
    @JsonBackReference("flight-origin")
    @Getter
    private Flight flight;
    @Getter
    private Airport airport;

    @Getter
    private LocalDateTime actualDepartureTime;

    public OriginMetadata() {
    }

    public OriginMetadata(Flight flight, Airport airport) {
        this.flight = flight;
        this.airport = airport;
        this.actualDepartureTime = LocalDateTime.now();
    }

    public void setActualDepartureTime(LocalDateTime actualDepartureTime) {
        if (actualDepartureTime == null) {
            throw new IllegalArgumentException("Actual departure time cannot be null.");
        }
        if (actualDepartureTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Actual departure time cannot be in the future.");
        }

        this.actualDepartureTime = actualDepartureTime;
    }
}
