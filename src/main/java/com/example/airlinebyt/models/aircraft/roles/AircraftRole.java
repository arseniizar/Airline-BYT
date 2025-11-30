package com.example.airlinebyt.models.aircraft.roles;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = Void.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommercialRole.class, name = "commercial"),
        @JsonSubTypes.Type(value = PrivateRole.class, name = "private")
})
public abstract class AircraftRole {
    public AircraftRole() {}
}
