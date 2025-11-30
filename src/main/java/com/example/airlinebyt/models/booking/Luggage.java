package com.example.airlinebyt.models.booking;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Luggage implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final double MAX_WEIGHT_PER_SUITCASE = 28.0;

    private int numberOfSuitcases;

    @Setter
    @OneToOne(mappedBy = "luggage")
    private Booking booking;

    public Luggage(int numberOfSuitcases) {
        setNumberOfSuitcases(numberOfSuitcases);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberOfSuitcases(int numberOfSuitcases) {
        if (numberOfSuitcases < 0) {
            throw new IllegalArgumentException("Number of suitcases cannot be negative.");
        }
        this.numberOfSuitcases = numberOfSuitcases;
    }

    public double getTotalMaxWeight() {
        return MAX_WEIGHT_PER_SUITCASE * numberOfSuitcases;
    }
}
