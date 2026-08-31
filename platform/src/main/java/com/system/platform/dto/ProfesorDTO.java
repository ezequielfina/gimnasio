package com.system.platform.dto;

import com.system.platform.entities.operaciones.Profesor;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class ProfesorDTO {
    public ProfesorDTO () {}

    public record Base (
            UUID id
    ) {}

    public record Create (
            SedeDTO.Base sede,
            @Nullable String nombre,
            String apellido
    ) {}

    public record Update (
            @Nullable SedeDTO.Base sede,
            @Nullable String nombre,
            @Nullable String apellido
    ) {}

    public record Response (
            UUID id,
            SedeDTO.Response sede,
            @Nullable String nombre,
            String apellido
    ) {
        public static Response fromEntity (Profesor data) {
            return new Response(
                    data.getId(),
                    SedeDTO.Response.fromEntity(data.getSede()),
                    data.getNombre(),
                    data.getApellido()
            );
        }
    }
}
