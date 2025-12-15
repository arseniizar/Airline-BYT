package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.operations.Airport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = Void.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FixedWingAircraft.class, name = "fixed_wing"),
        @JsonSubTypes.Type(value = RotorcraftAircraft.class, name = "rotorcraft")
})
public abstract class Aircraft implements BaseEntity {

    private Long id;
    @Getter
    private String model;
    @Getter
    private int capacity;
    private AircraftRole currentRole;
    @Setter
    @Getter
    private Airport baseAirport;
    public static final int MAX_ALLOWED_CAPACITY = 1000; //idk change value if u want
    public static final int MIN_ALLOWED_CAPACITY= 1000; //idk change value if u want

    // 2. Composition association
    private final Set<Seat> seats = new HashSet<>();


    public Aircraft() {
    }

    public Aircraft(String model, int capacity, AircraftRole currentRole) {
        this.setModel(model);
        this.setCapacity(capacity);
        this.currentRole = currentRole;
        this.baseAirport = null;
    }

    public AircraftRole getRole() {
        return this.currentRole;
    }

    public void setRole(AircraftRole role) {
        this.currentRole = role;
    }

    public void prepareForFlight() {
        System.out.println("Starting standard technical checks for model: " + this.model);

        if (currentRole instanceof CommercialRole commercial) {
            System.out.println("[ROLE-LOGIC] Performing commercial checks. Cargo capacity set to: " + commercial.getCargoCapacity());
        } else if (currentRole instanceof PrivateRole privateRole) {
            System.out.println("[ROLE-LOGIC] Preparing private interior: " + privateRole.getPersonalizedInterior());
        } else {
            throw new IllegalStateException("[EXCEPTION] Unknown role, initialize it or change it.");
        }

        System.out.println("Aircraft is ready for flight.");
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty.");
        }
        this.model = model;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative. Provided value: " + capacity);
        }
        this.capacity = capacity;
    }

    public abstract String getType();

    public void addSeat(String seatNumber, SeatClass seatClass) {
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be empty.");
        }
        if (seatClass == null) {
            throw new IllegalArgumentException("Seat class cannot be null.");
        }
        Seat newSeat = new Seat(seatNumber, seatClass, this);
        this.seats.add(newSeat);
    }

    public Optional<Seat> getSeat(String seatNumber) {
        return this.seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber))
                .findFirst();
    }
}
