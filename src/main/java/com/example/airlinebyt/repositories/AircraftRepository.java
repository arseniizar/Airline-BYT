package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.aircraft.Aircraft;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AircraftRepository extends InMemoryRepository<Aircraft> {
    public AircraftRepository() {
        super(Aircraft.class, new TypeReference<Map<Long, Aircraft>>() {
        });
    }
}
