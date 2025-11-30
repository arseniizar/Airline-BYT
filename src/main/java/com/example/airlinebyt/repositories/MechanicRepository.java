package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.person.Mechanic;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class MechanicRepository extends InMemoryRepository<Mechanic> {
    public MechanicRepository() {
        super(Mechanic.class, new TypeReference<Map<Long, Mechanic>>() {});
    }
}
