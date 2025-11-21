package com.example.airlinebyt.services;

import com.example.airlinebyt.models.operations.Airport;
import com.example.airlinebyt.repositories.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends GenericCrudService<Airport, AirportRepository> {
    public AirportService(AirportRepository repository) {
        super(repository);
    }
}