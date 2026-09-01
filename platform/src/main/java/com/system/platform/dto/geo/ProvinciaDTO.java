package com.system.platform.dto.geo;

import com.system.platform.entities.geo.Provincia;

import java.util.UUID;

public final class ProvinciaDTO {
    private ProvinciaDTO() {}

    public record Base(
            UUID id
    ) {}

    public record Create(
            String nombre
    ) {}

    public record Update(
            String nombre
    ) {}

    public record Response(
            UUID id,
            String nombre
    ) {
        public static Response fromEntity (Provincia provincia) {
            return new Response(
                    provincia.getId(),
                    provincia.getNombre()
            );
        }
    }
}
