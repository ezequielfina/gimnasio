package com.system.platform.dto.operaciones;

import com.system.platform.dto.geo.LocalidadDTO;
import com.system.platform.entities.operaciones.Sede;
import com.system.platform.entities.operaciones.TipoSede;
import jakarta.annotation.Nullable;

import java.util.UUID;

public final class SedeDTO {
    public SedeDTO () {}

    public record Base (
        UUID id
    ) {}

    public record Create (
            String nombre,
            TipoSede tipoSede,
            boolean isEnabled,
            LocalidadDTO.Base localidad,
            @Nullable String direccion
    ) {}

    public record Update (
            @Nullable String nombre,
            @Nullable TipoSede tipoSede,
            @Nullable Boolean isEnabled,
            @Nullable LocalidadDTO.Base localidad,
            @Nullable String direccion
    ) {}

    public record Response (
            UUID id,
            String nombre,
            TipoSede tipoSede,
            boolean isEnabled,
            LocalidadDTO.Response localidad,
            @Nullable String direccion
    ) {
        public static Response fromEntity(Sede data) {
            return new SedeDTO.Response(
                    data.getId(),
                    data.getNombre(),
                    data.getTipoSede(),
                    data.getIsEnabled(),
                    LocalidadDTO.Response.fromEntity(data.getLocalidad()),
                    data.getDireccion()
            );
        }
    }
}
