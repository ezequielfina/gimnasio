package com.system.platform.dto;

import com.system.platform.entities.Localidad;
import com.system.platform.entities.Partido;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class LocalidadDTO {
    public record Base (
        UUID id
    ) {}

    public record Create (
        String nombre,
        PartidoDTO.Base partido
    ) {}

    public record Update (
        @Nullable String nombre,
        @Nullable PartidoDTO.Base partido
    ) {}

    public record Response (
        UUID id,
        String nombre,
        PartidoDTO.Response partido
    ) {
        public static Response fromEntity(Localidad localidad) {
            return new Response(
                    localidad.getId(),
                    localidad.getNombre(),
                    localidad.getPartido() != null ? PartidoDTO.Response.fromEntity(localidad.getPartido()) : null
            );
        }
    }
}
