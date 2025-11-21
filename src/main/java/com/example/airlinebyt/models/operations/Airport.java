package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.embeddable.Location;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Airport implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 3)
    private String iataCode;

    private String name;

    @Embedded
    private Location location;
}
