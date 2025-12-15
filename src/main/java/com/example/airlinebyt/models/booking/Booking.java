package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Passenger;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Booking implements BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Setter @Enumerated(EnumType.STRING) private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING) private BookingStatus bookingStatus;
    private BigDecimal bookingFee;
    private BigDecimal totalPrice;

    @JsonBackReference("passenger-bookings")
    @Transient
    private Passenger passenger;

    // 3. Aggregation association

    private Set<Ticket> tickets;

    @Setter @Transient private Luggage luggage;

    public Booking(BigDecimal totalPrice, Passenger passenger, Set<Ticket> tickets) {
        setTotalPrice(totalPrice);
        setPassenger(passenger);
        setTickets(tickets);
        this.bookingStatus = BookingStatus.IN_CART;
    }

    public void setPassenger(Passenger newPassenger) {
        if (this.passenger != null && !this.passenger.equals(newPassenger)) {
            this.passenger.removeBooking(this);
        }
        this.passenger = newPassenger;
        if (newPassenger != null && !newPassenger.getBookings().contains(this)) {
            newPassenger.addBooking(this);
        }
    }

    public Ticket createTicket(BigDecimal price, Flight flight, Seat seat) {
        if (price == null || flight == null || seat == null) {
            throw new IllegalArgumentException("Price, flight, and seat are required.");
        }
        String ticketNumber = UUID.randomUUID().toString();
        Ticket newTicket = new Ticket(ticketNumber, price, this, flight, seat);
        this.tickets.add(newTicket);
        return newTicket;
    }

    public void removeTicket(Ticket ticket) {
        if (ticket != null && ticket.getBooking() == this) {
            this.tickets.remove(ticket);
        }
    }

    public Set<Ticket> getTickets() { return Collections.unmodifiableSet(tickets); }

    // --- Standard class methods ---
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        if (bookingStatus == null) {
            throw new IllegalArgumentException("Booking status cannot be null.");
        }
        this.bookingStatus = bookingStatus;
    }

    public void setBookingFee(BigDecimal bookingFee) {
        if (bookingFee != null && bookingFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Booking fee cannot be negative.");
        }
        this.bookingFee = bookingFee;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total price cannot be null or negative.");
        }
        this.totalPrice = totalPrice;
    }

    private void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets != null ? tickets : new HashSet<>();
    }
}
