package com.system.platform.dto;

import com.system.platform.entities.Partido;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class PartidoDTO {
    private PartidoDTO() {}

    public record Base(UUID id) {}

    public record Create(
            String nombre,
            ProvinciaDTO.Base provincia
    ) {}

    // Incluye el ID del partido y la provincia (opcional en el payload)
    public record Update(
            @Nullable String nombre,
            @Nullable ProvinciaDTO.Base provincia
    ) {}

    public record Response(
            UUID id,
            String nombre,
            ProvinciaDTO.Response provincia
    ) {
        // Debe ser estático para instanciarse sin un objeto previo
        public static Response fromEntity(Partido partido) {
            return new Response(
                    partido.getId(),
                    partido.getNombre(),
                    partido.getProvincia() != null ? ProvinciaDTO.Response.fromEntity(partido.getProvincia()) : null
            );
        }
    }
}
