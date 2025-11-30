package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.operations.Issue;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class IssueRepository extends InMemoryRepository<Issue> {
    public IssueRepository() {
        super(Issue.class, new TypeReference<Map<Long, Issue>>() {});
    }
}