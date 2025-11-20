package com.example.airlinebyt.repositories;

import com.example.airlinebyt.models.booking.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TicketRepository extends InMemoryRepository<Ticket> {
    public TicketRepository() {
        super(Ticket.class, new TypeReference<Map<Long, Ticket>>() {
        });
    }
}
