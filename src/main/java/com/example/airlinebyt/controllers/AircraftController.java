package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.services.AircraftService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController extends GenericCrudController<Aircraft, AircraftService> {
    public AircraftController(AircraftService service) {
        super(service);
    }
}