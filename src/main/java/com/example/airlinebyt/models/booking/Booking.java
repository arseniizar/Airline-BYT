package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.BookingStatus;
import com.example.airlinebyt.enums.PaymentMethod;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.person.Passenger;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Booking implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private BigDecimal bookingFee;
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "luggage_id", referencedColumnName = "id")
    private Luggage luggage;
}
