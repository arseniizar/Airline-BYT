package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.person.Passenger;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class PassengerRepository extends InMemoryRepository<Passenger> {
    public PassengerRepository() {
        super(Passenger.class, new TypeReference<Map<Long, Passenger>>() {});
    }
}
