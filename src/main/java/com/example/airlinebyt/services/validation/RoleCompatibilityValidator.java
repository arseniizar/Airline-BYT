package com.example.airlinebyt.services.validation;

import com.example.airlinebyt.models.aircraft.roles.AircraftRole;
import com.example.airlinebyt.models.aircraft.roles.CommercialRole;
import com.example.airlinebyt.models.aircraft.roles.PrivateRole;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.*;

@Component
public class RoleCompatibilityValidator {

    private final Map<Class<? extends AircraftRole>, Set<Class<? extends AircraftRole>>> conflictMatrix = new HashMap<>();

    @PostConstruct
    private void initializeRules() {
        addConflict(CommercialRole.class, PrivateRole.class);
    }

    private void addConflict(Class<? extends AircraftRole> roleA, Class<? extends AircraftRole> roleB) {
        conflictMatrix.computeIfAbsent(roleA, k -> new HashSet<>()).add(roleB);
        conflictMatrix.computeIfAbsent(roleB, k -> new HashSet<>()).add(roleA);
    }

    public void validate(AircraftRole newRole, Collection<AircraftRole> existingRoles) {
        Class<?> newRoleClass = newRole.getClass();
        Set<Class<? extends AircraftRole>> conflicts = conflictMatrix.getOrDefault(newRoleClass, Collections.emptySet());

        for (AircraftRole existingRole : existingRoles) {
            if (conflicts.contains(existingRole.getClass())) {
                throw new IllegalStateException(
                        String.format("Role %s cannot be added because it conflicts with the existing role %s.",
                                newRoleClass.getSimpleName(),
                                existingRole.getClass().getSimpleName())
                );
            }
        }
    }
}
