package com.system.platform.dto.operaciones;

import com.system.platform.entities.operaciones.EstadoSesion;
import com.system.platform.entities.operaciones.Sesion;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class SesionDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
            ClaseDTO.Base clase,
            EspacioDTO.Base espacio,
            LocalDate fecha,
            EstadoSesion estadoSesion
    ) {}

    public record Update (
            @Nullable ClaseDTO.Base clase,
            @Nullable EspacioDTO.Base espacio,
            @Nullable Date fecha,
            @Nullable EstadoSesion estadoSesion
    ) {}

    public record Response (
            UUID id,
            ClaseDTO.Response clase,
            EspacioDTO.Response espacio,
            LocalDate fecha,
            EstadoSesion estadoSesion
    ) {
        public static Response fromEntity(Sesion data) {
            return new Response(
                    data.getId(),
                    ClaseDTO.Response.fromEntity(data.getClase()),
                    EspacioDTO.Response.fromEntity(data.getEspacio()),
                    data.getFecha(),
                    data.getEstado()
            );
        }
    }
}
