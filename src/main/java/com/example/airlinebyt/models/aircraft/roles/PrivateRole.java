
package com.example.airlinebyt.models.aircraft.roles;

import lombok.Getter;

@Getter
public class PrivateRole extends AircraftRole {

    private String personalizedInterior;

    public PrivateRole() {
    }

    public PrivateRole(String personalizedInterior) {
        this.setPersonalizedInterior(personalizedInterior);
    }

    public void setPersonalizedInterior(String personalizedInterior) {
        if (personalizedInterior == null || personalizedInterior.trim().isEmpty()) {
            throw new IllegalArgumentException("Personalized interior description cannot be null or empty.");
        }
        this.personalizedInterior = personalizedInterior;
    }

    @Override
    public String toString() {
        return "PrivateRole{" +
                "personalizedInterior='" + personalizedInterior + '\'' +
                '}';
    }
}
