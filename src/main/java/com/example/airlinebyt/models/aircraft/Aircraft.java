package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.enums.ConstructionType;
import com.example.airlinebyt.enums.RoleType;
import com.example.airlinebyt.enums.SeatClass;
import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.booking.Seat;
import com.example.airlinebyt.models.operations.Airport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft implements BaseEntity {

    private Long id;

    @Getter
    private String model;
    @Getter
    private int capacity;

    @Setter
    @Getter
    private Airport baseAirport;

    @Getter
    @Setter
    private ConstructionType constructionType;

    @Getter
    @Setter
    private RoleType roleType;

    private Double wingLength;
    private Integer landingWheels;

    private Integer amountOfRotors;
    private Double maxHoverAltitude;

    private Double cargoCapacity;

    private String personalizedInterior;

    private final Set<Seat> seats = new HashSet<>();

    public Aircraft() {
    }

    public Aircraft(String model, int capacity, ConstructionType constructionType, RoleType roleType) {
        setModel(model);
        setCapacity(capacity);
        this.constructionType = constructionType;
        this.roleType = roleType;
    }

    public void setFixedWingAttributes(Double wingLength, Integer landingWheels) {
        if (this.constructionType != ConstructionType.FIXED_WING) {
            throw new IllegalStateException("Cannot set FixedWing attributes for a " + this.constructionType);
        }
        this.wingLength = wingLength;
        this.landingWheels = landingWheels;
    }

    public Double getWingLength() {
        validateConstructionType(ConstructionType.FIXED_WING);
        return wingLength;
    }

    public Integer getLandingWheels() {
        validateConstructionType(ConstructionType.FIXED_WING);
        return landingWheels;
    }

    public void setRotorcraftAttributes(Integer amountOfRotors, Double maxHoverAltitude) {
        if (this.constructionType != ConstructionType.ROTORCRAFT) {
            throw new IllegalStateException("Cannot set Rotorcraft attributes for a " + this.constructionType);
        }
        this.amountOfRotors = amountOfRotors;
        this.maxHoverAltitude = maxHoverAltitude;
    }

    public Integer getAmountOfRotors() {
        validateConstructionType(ConstructionType.ROTORCRAFT);
        return amountOfRotors;
    }

    public Double getMaxHoverAltitude() {
        validateConstructionType(ConstructionType.ROTORCRAFT);
        return maxHoverAltitude;
    }

    public void setCommercialAttributes(Double cargoCapacity) {
        if (this.roleType != RoleType.COMMERCIAL) {
            throw new IllegalStateException("Cannot set Commercial attributes for a " + this.roleType);
        }
        this.cargoCapacity = cargoCapacity;
    }

    public Double getCargoCapacity() {
        validateRoleType(RoleType.COMMERCIAL);
        return cargoCapacity;
    }

    public void setPrivateAttributes(String personalizedInterior) {
        if (this.roleType != RoleType.PRIVATE) {
            throw new IllegalStateException("Cannot set Private attributes for a " + this.roleType);
        }
        this.personalizedInterior = personalizedInterior;
    }

    public String getPersonalizedInterior() {
        validateRoleType(RoleType.PRIVATE);
        return personalizedInterior;
    }

    private void validateConstructionType(ConstructionType expected) {
        if (this.constructionType != expected) {
            throw new IllegalStateException("Field not available for construction type: " + this.constructionType);
        }
    }

    private void validateRoleType(RoleType expected) {
        if (this.roleType != expected) {
            throw new IllegalStateException("Field not available for role type: " + this.roleType);
        }
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
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.capacity = capacity;
    }


    public void addSeat(String seatNumber, SeatClass seatClass) {
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be empty.");
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
