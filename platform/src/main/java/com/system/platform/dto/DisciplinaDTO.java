package com.system.platform.dto;

import com.system.platform.entities.operaciones.Disciplina;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class DisciplinaDTO {
    public DisciplinaDTO () {}

    public record Base (
            UUID id
    ) {}

    public record Create (
            String nombre,
            @Nullable String descripcion
    ) {}

    public record Update (
            @Nullable String nombre,
            @Nullable String descripcion
    ) {}

    public record Response (
            UUID id,
            String nombre,
            String descripcion
    ) {
        public static Response fromEntity (Disciplina data) {
            return new Response(
                    data.getId(),
                    data.getNombre(),
                    data.getDescripcion()
            );
        }
    }
}
