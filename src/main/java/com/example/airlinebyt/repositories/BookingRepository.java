package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.booking.Booking;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BookingRepository extends InMemoryRepository<Booking> {
    public BookingRepository() {
        super(Booking.class, new TypeReference<Map<Long, Booking>>() {
        });
    }
}
