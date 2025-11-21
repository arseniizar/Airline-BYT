package com.example.airlinebyt.services;

import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.repositories.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends GenericCrudService<Booking, BookingRepository> {
    public BookingService(BookingRepository repository) {
        super(repository);
    }
}