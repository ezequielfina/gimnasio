package com.system.platform.dto;

import com.system.platform.entities.Provincia;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public final class ProvinciaDTO {
    private ProvinciaDTO() {}

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
