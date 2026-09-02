package com.system.platform.dto.auth;

import com.system.platform.dto.operaciones.SedeDTO;
import com.system.platform.entities.auth.AdminSede;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public final class AdminSedeDTO {
    public record Base (
            UUID id
    ) {}
    public record Create (
            UsuarioDTO.Base usuario,
            SedeDTO.Base sede
    ) {}

    public record Response (
            UUID id,
            UsuarioDTO.Response usuario,
            SedeDTO.Response sede
    ) {
        public static Response fromEntity (AdminSede data) {
            return new Response(
                    data.getId(),
                    UsuarioDTO.Response.fromEntity(data.getAdmin()),
                    SedeDTO.Response.fromEntity(data.getSede())
            );
        }
    }
}
