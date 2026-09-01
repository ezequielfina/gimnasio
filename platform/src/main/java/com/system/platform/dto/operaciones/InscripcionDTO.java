package com.system.platform.dto.operaciones;

import com.system.platform.dto.auth.UsuarioDTO;
import com.system.platform.entities.operaciones.Inscripcion;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public final class InscripcionDTO {
    public record Base(
            UUID id
    ) {}

    public record Create (
            UsuarioDTO.Base usuario,
            SesionDTO.Base sesion
    ) {}

    public record Response (
            UUID id,
            UsuarioDTO.Response usuario,
            SesionDTO.Response sesion,
            boolean asistio
    ) {
        public static Response fromEntity (Inscripcion data) {
            return new Response(
                    data.getId(),
                    UsuarioDTO.Response.fromEntity(data.getUsuario()),
                    SesionDTO.Response.fromEntity(data.getSesion()),
                    data.getAsistio()
            );
        }
    }
}
