package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class Ticket implements BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true) private String ticketNumber;
    private BigDecimal price;

    // --- ASSOCIATION: Composition ---
    @Transient
    private final Booking booking;

    @Transient private Flight flight;
    @Transient private Seat seat;

    protected Ticket() {
        this.booking = null;
    }

    Ticket(String ticketNumber, BigDecimal price, Booking booking, Flight flight, Seat seat) {
        if (booking == null) {
            throw new IllegalArgumentException("Ticket must belong to a Booking.");
        }
        this.booking = booking;
        setTicketNumber(ticketNumber);
        setPrice(price);
        setFlight(flight);
        setSeat(seat);
    }

    // --- Standard class methods ---
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setTicketNumber(String ticketNumber) {
        if (ticketNumber == null || ticketNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket number cannot be empty.");
        }
        this.ticketNumber = ticketNumber;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative.");
        }
        this.price = price;
    }

    public void setFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null.");
        }
        this.flight = flight;
    }

    public void setSeat(Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("Seat cannot be null.");
        }
        this.seat = seat;
    }
}
