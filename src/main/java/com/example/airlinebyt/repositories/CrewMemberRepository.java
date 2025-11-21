package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.person.CrewMember;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CrewMemberRepository extends InMemoryRepository<CrewMember> {
    public CrewMemberRepository() {
        super(CrewMember.class, new TypeReference<Map<Long, CrewMember>>() {
        });
    }
}