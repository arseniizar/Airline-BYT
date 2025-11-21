package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seat implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
}
