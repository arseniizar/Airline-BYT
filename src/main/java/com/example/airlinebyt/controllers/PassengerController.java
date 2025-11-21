package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.person.Passenger;
import com.example.airlinebyt.services.PassengerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController extends GenericCrudController<Passenger, PassengerService> {
    public PassengerController(PassengerService service) {
        super(service);
    }
}