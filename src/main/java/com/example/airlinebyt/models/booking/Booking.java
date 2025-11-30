package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.person.CrewMember;
import com.example.airlinebyt.models.person.Passenger;
import com.example.airlinebyt.models.person.Pilot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Transient
    private Passenger passenger;

    @Setter
    @Transient
    private List<Ticket> tickets = new ArrayList<>();

    @Setter
    @Transient
    private Luggage luggage;

    public Booking(BigDecimal totalPrice, Passenger passenger) {
        setTotalPrice(totalPrice);
        setPassenger(passenger);
        this.bookingStatus = BookingStatus.IN_CART;
    }

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

    public void setPassenger(Passenger passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null.");
        }
        this.passenger = passenger;
    }

}
