package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.person.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EmployeeRepository extends InMemoryRepository<Employee> {
    public EmployeeRepository() {
        super(Employee.class, new TypeReference<Map<Long, Employee>>() {
        });
    }
}