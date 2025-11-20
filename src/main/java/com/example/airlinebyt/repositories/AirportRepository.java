package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.operations.Airport;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AirportRepository extends InMemoryRepository<Airport> {
    public AirportRepository() {
        super(Airport.class, new TypeReference<Map<Long, Airport>>() {
        });
    }
}