package com.example.airlinebyt.controllers;

import com.example.airlinebyt.models.booking.Booking;
import com.example.airlinebyt.services.BookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController extends GenericCrudController<Booking, BookingService> {
    public BookingController(BookingService service) {
        super(service);
    }
}