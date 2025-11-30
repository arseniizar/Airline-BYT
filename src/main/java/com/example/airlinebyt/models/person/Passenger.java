package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.booking.Booking;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Passenger extends Person {
    @Getter
    @Column(unique = true)
    private String passengerID; // Наприклад, номер часто літаючого пасажира

    @Getter
    private String email;

    @Getter
    private String contactNumber;

    @Getter
    private Integer loyaltyPoints = 0;

    @Getter
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Passenger(String firstName, String lastName, LocalDate birthDate,
                     String passengerID, String email, String contactNumber) {
        super(firstName, lastName, birthDate);
        setPassengerID(passengerID);
        setEmail(email);
        setContactNumber(contactNumber);
    }

    public void setPassengerID(String passengerID) {
        if (passengerID == null || passengerID.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".passengerID cannot be empty");
        }
        this.passengerID = passengerID;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".email cannot be empty");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException(this.getClass().getName() + ".email format is invalid");
        }

        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".contactNumber cannot be empty");
        }

        String phoneRegex = "^\\+?[0-9]{7,15}$";
        if (!contactNumber.matches(phoneRegex)) {
            throw new IllegalArgumentException(this.getClass().getName() + ".contactNumber format is invalid");
        }

        this.contactNumber = contactNumber;
    }

    public void addLoyaltyPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".points to add cannot be negative");
        }

        this.loyaltyPoints += points;
    }

    public void redeemLoyaltyPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException(this.getClass().getName() + ".points to redeem cannot be negative");
        }

        if (points > this.loyaltyPoints) {
            throw new IllegalArgumentException(this.getClass().getName() + ".not enough loyalty points to redeem");
        }

        this.loyaltyPoints -= points;
    }


    // Метод з діаграми
    public void printLoyaltyPoints() {
        System.out.println("Current loyalty points: " + this.loyaltyPoints);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
