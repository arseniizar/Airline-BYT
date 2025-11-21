package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Luggage implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final double MAX_WEIGHT_PER_SUITCASE = 28.0;

    private int numberOfSuitcases;

    @OneToOne(mappedBy = "luggage")
    private Booking booking;

    public double getTotalMaxWeight() {
        return MAX_WEIGHT_PER_SUITCASE * numberOfSuitcases;
    }
}
