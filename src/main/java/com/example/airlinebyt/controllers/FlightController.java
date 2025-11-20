package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.services.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
public class FlightController extends GenericCrudController<Flight, FlightService> {
    public FlightController(FlightService service) {
        super(service);
    }
}