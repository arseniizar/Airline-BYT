package com.example.airlinebyt.services;

import com.example.airlinebyt.models.aircraft.Aircraft;
import com.example.airlinebyt.repositories.AircraftRepository;
import org.springframework.stereotype.Service;

@Service
public class AircraftService extends GenericCrudService<Aircraft, AircraftRepository> {
    public AircraftService(AircraftRepository repository) {
        super(repository);
    }
}