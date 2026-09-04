package com.system.platform.dto.comercial;

import com.system.platform.entities.comercial.Plan;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
public final class PlanDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
        String nombre,
        boolean isEnabled,
        @Nullable PlanDTO.Base planPadre,
        Map<String, Object> permisos
    ) {}

    public record Update (
            @Nullable String nombre,
            @Nullable Boolean isEnabled,
            @Nullable PlanDTO.Base planPadre,
            @Nullable Object permisos
    ) {}

    public record Response (
            UUID id,
            String nombre,
            boolean isEnabled,
            @Nullable PlanDTO.Response planPadre,
            Object permisos
    ) {
        public static Response fromEntity (Plan data) {
            return new PlanDTO.Response (
                    data.getId(),
                    data.getNombre(),
                    data.getIsEnabled(),
                    data.getPlanPadre() != null ? Response.fromEntity(data.getPlanPadre()) : null,
                    data.getPermisos()
            );
        }
    }

}
