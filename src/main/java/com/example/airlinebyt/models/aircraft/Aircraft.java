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
    @Getter
    @Setter
    private Airport baseAirport;

    @Getter
    private ConstructionType constructionType;
    @Getter
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

        if (constructionType == null || roleType == null) {
            throw new IllegalArgumentException("ConstructionType and RoleType are mandatory!");
        }
        this.constructionType = constructionType;
        this.roleType = roleType;
    }

    public void setFixedWingAttributes(Double wingLength, Integer landingWheels) {
        validateType(ConstructionType.FIXED_WING);
        if (wingLength == null || wingLength <= 0) throw new IllegalArgumentException("Invalid wing length");
        this.wingLength = wingLength;
        this.landingWheels = landingWheels;
    }

    public Double getWingLength() {
        validateType(ConstructionType.FIXED_WING);
        return wingLength;
    }

    public Integer getLandingWheels() {
        validateType(ConstructionType.FIXED_WING);
        return landingWheels;
    }

    public void setRotorcraftAttributes(Integer amountOfRotors, Double maxHoverAltitude) {
        validateType(ConstructionType.ROTORCRAFT);
        if (amountOfRotors == null || amountOfRotors <= 0) throw new IllegalArgumentException("Invalid rotors count");
        this.amountOfRotors = amountOfRotors;
        this.maxHoverAltitude = maxHoverAltitude;
    }

    public Integer getAmountOfRotors() {
        validateType(ConstructionType.ROTORCRAFT);
        return amountOfRotors;
    }

    public Double getMaxHoverAltitude() {
        validateType(ConstructionType.ROTORCRAFT);
        return maxHoverAltitude;
    }

    public void setCommercialAttributes(Double cargoCapacity) {
        validateRole(RoleType.COMMERCIAL);
        if (cargoCapacity < 0) throw new IllegalArgumentException("Cargo capacity cannot be negative");
        this.cargoCapacity = cargoCapacity;
    }

    public Double getCargoCapacity() {
        validateRole(RoleType.COMMERCIAL);
        return cargoCapacity;
    }

    public void setPrivateAttributes(String personalizedInterior) {
        validateRole(RoleType.PRIVATE);
        this.personalizedInterior = personalizedInterior;
    }

    public String getPersonalizedInterior() {
        validateRole(RoleType.PRIVATE);
        return personalizedInterior;
    }

    private void validateType(ConstructionType expected) {
        if (this.constructionType != expected) {
            throw new IllegalStateException("Operation not allowed. Aircraft is " + this.constructionType + ", but expected " + expected);
        }
    }

    private void validateRole(RoleType expected) {
        if (this.roleType != expected) {
            throw new IllegalStateException("Operation not allowed. Aircraft role is " + this.roleType + ", but expected " + expected);
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
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model required");
        this.model = model;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be positive");
        this.capacity = capacity;
    }

    public void addSeat(String seatNumber, SeatClass seatClass) {
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be empty.");
        }
        this.seats.add(new Seat(seatNumber, seatClass, this));
    }

    public Optional<Seat> getSeat(String seatNumber) {
        return this.seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber))
                .findFirst();
    }
}
