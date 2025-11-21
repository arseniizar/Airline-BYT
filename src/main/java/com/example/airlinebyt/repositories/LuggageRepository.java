package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.booking.Luggage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class LuggageRepository extends InMemoryRepository<Luggage> {
    public LuggageRepository() {
        super(Luggage.class, new TypeReference<Map<Long, Luggage>>() {
        });
    }
}