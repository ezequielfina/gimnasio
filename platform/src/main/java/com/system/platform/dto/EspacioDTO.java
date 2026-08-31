package com.system.platform.dto;

import com.system.platform.entities.operaciones.Espacio;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class EspacioDTO {
    public EspacioDTO () {}

    public record Base (
            UUID id
    ) {}

    public record Create (
            String nombre,
            Integer cupoMaximo,
            SedeDTO.Base sede
    ) {}

    public record Update (
            @Nullable String nombre,
            @Nullable Integer cupoMaximo,
            @Nullable SedeDTO.Base sede
    ) {}

    public record Response (
            UUID id,
            String nombre,
            Integer cupoMaximo,
            SedeDTO.Response sede
    ) {
        public static Response fromEntity(Espacio data) {
            return new Response(
                    data.getId(),
                    data.getNombre(),
                    data.getCupoMaximo(),
                    SedeDTO.Response.fromEntity(data.getSede())
            );
        }
    }
}
