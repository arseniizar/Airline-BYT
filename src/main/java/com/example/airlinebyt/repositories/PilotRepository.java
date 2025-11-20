package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.person.Pilot;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PilotRepository extends InMemoryRepository<Pilot> {
    public PilotRepository() {
        super(Pilot.class, new TypeReference<Map<Long, Pilot>>() {
        });
    }
}