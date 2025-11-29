package com.example.airlinebyt.models.embeddable;


import lombok.Getter;

public class Location {
    @Getter
    private String country;
    @Getter
    private String city;

    public Location(String country, String city) throws IllegalArgumentException {
        setCountry(country);
        setCity(city);
    }

    public void setCountry(String country) throws IllegalArgumentException {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".country cannot be empty");
        }

        this.country = country;
    }

    public void setCity(String city) throws IllegalArgumentException {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getName() + ".city cannot be empty");
        }

        this.city = city;
    }
}
