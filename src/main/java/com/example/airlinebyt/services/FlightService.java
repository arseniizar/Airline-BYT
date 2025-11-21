package com.example.airlinebyt.services;

import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.repositories.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends GenericCrudService<Flight, FlightRepository> {
    public FlightService(FlightRepository repository) {
        super(repository);
    }
}