package com.example.airlinebyt.models.operations;

import com.example.airlinebyt.models.BaseEntity;
import com.example.airlinebyt.models.aircraft.Aircraft;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue implements BaseEntity {

    private Long id;
    private String description;

    private boolean isResolved = false;

    private transient Aircraft source;

    public Issue(String description, Aircraft source) {
        setDescription(description);
        setSource(source);
    }

    @JsonProperty("isResolved")
    public boolean isResolved() {
        return isResolved;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Issue description cannot be empty.");
        }
        this.description = description;
    }

    public void setSource(Aircraft source) {
        if (source == null) {
            throw new IllegalArgumentException("Issue must have a source aircraft.");
        }
        this.source = source;
    }

    public void markAsResolved() {
        this.isResolved = true;
    }
}
