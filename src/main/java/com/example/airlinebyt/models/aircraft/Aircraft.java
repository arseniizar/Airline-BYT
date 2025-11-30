package com.example.airlinebyt.models.aircraft;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

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

    public Aircraft() {
    }

    public Aircraft(String model, int capacity, AircraftRole currentRole) {
        this.setModel(model);
        this.setCapacity(capacity);
        this.currentRole = currentRole;
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
}
