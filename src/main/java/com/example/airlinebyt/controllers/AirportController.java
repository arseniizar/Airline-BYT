package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.services.AirportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airports")
public class AirportController extends GenericCrudController<Airport, AirportService> {
    public AirportController(AirportService service) {
        super(service);
    }
}