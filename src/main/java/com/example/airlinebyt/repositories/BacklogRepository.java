package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.operations.Backlog;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class BacklogRepository extends InMemoryRepository<Backlog> {
    public BacklogRepository() {
        super(Backlog.class, new TypeReference<Map<Long, Backlog>>() {});
    }
}
