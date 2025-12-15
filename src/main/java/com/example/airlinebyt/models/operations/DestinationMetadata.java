package com.example.airlinebyt.models.operations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import java.time.LocalDateTime;

public class DestinationMetadata {
    @Getter
    @JsonBackReference("flight-destination")
    private Flight flight;
    @Getter
    private Airport airport;

    @Getter
    private LocalDateTime actualArrivalTime;

    public DestinationMetadata() {
    }

    public DestinationMetadata(Flight flight, Airport airport) {
        this.flight = flight;
        this.airport = airport;
        this.actualArrivalTime = LocalDateTime.now();
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        if (actualArrivalTime == null) {
            throw new IllegalArgumentException("Actual arrival time cannot be null.");
        }
        if (actualArrivalTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Actual arrival time cannot be in the future.");
        }

        this.actualArrivalTime = actualArrivalTime;
    }
}
