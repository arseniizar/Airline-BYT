package com.example.airlinebyt.services;

import com.example.airlinebyt.models.person.Employee;
import com.example.airlinebyt.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends GenericCrudService<Employee, EmployeeRepository> {
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }
}