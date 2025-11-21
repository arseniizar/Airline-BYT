package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.booking.Seat;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SeatRepository extends InMemoryRepository<Seat> {
    public SeatRepository() {
        super(Seat.class, new TypeReference<Map<Long, Seat>>() {
        });
    }
}