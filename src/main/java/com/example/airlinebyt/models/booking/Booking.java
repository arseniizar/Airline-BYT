package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.operations.Flight;
import com.example.airlinebyt.models.person.Passenger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Booking implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private BigDecimal bookingFee;
    private BigDecimal totalPrice;

    // --- ASSOCIATION: Basic (1..*) ---
    @Transient
    private Passenger passenger;

    // --- ASSOCIATION: Composition ---
    @Setter
    @Transient
    private List<Ticket> tickets = new ArrayList<>();

    @Setter
    @Transient
    private Luggage luggage;

    public Booking(BigDecimal totalPrice, Passenger passenger) {
        setTotalPrice(totalPrice);
        setPassenger(passenger); // Встановлення зв'язку при створенні
        this.bookingStatus = BookingStatus.IN_CART;
    }

    // --- ASSOCIATION MANAGEMENT (Passenger) ---
    public void setPassenger(Passenger newPassenger) {
        if (newPassenger == null) {
            throw new IllegalArgumentException("Booking must be associated with a passenger.");
        }
        if (this.passenger != null && !this.passenger.equals(newPassenger)) {
            this.passenger.removeBookingInternal(this);
        }
        this.passenger = newPassenger;
        newPassenger.addBookingInternal(this);
    }

    // --- ASSOCIATION MANAGEMENT (Composition with Ticket) ---
    public Ticket createTicket(BigDecimal price, Flight flight, Seat seat) {
        if (price == null || flight == null || seat == null) {
            throw new IllegalArgumentException("Price, flight, and seat are required to create a ticket.");
        }
        String ticketNumber = UUID.randomUUID().toString();
        Ticket newTicket = new Ticket(ticketNumber, price, this, flight, seat);
        this.tickets.add(newTicket);
        return newTicket;
    }

    public void removeTicket(Ticket ticket) {
        if (ticket == null || !ticket.getBooking().equals(this)) {
            throw new IllegalArgumentException("This ticket does not belong to this booking.");
        }
        this.tickets.remove(ticket);
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        if (bookingStatus == null) throw new IllegalArgumentException("Booking status cannot be null.");
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
}
