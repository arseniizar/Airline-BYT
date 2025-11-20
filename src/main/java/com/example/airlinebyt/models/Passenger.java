package com.example.airlinebyt.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("PASSENGER") // Значення в колонці person_type для пасажира
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Passenger extends Person {

    @Column(unique = true)
    private String passengerID; // Наприклад, номер часто літаючого пасажира

    private String email;
    private String contactNumber;
    private Integer loyaltyPoints = 0;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    // Метод з діаграми
    public void getLoyaltyPoints() {
        System.out.println("Current loyalty points: " + this.loyaltyPoints);
    }
}
