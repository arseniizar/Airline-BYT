package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.person.Employee;
import com.example.airlinebyt.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends GenericCrudController<Employee, EmployeeService> {
    public EmployeeController(EmployeeService service) {
        super(service);
    }
}