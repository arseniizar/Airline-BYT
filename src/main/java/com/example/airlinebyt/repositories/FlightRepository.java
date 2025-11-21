package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.operations.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class FlightRepository extends InMemoryRepository<Flight> {
    public FlightRepository() {
        super(Flight.class, new TypeReference<Map<Long, Flight>>() {
        });
    }
}
