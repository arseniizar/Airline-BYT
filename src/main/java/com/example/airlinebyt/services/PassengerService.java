package com.example.airlinebyt.services;

import com.example.airlinebyt.models.person.Passenger;
import com.example.airlinebyt.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class PassengerService extends GenericCrudService<Passenger, PassengerRepository> {
    public PassengerService(PassengerRepository repository) {
        super(repository);
    }
}