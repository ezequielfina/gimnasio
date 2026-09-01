package com.system.platform.dto.auth;

import com.system.platform.entities.auth.Rol;
import com.system.platform.entities.auth.Usuario;

import java.util.UUID;

public final class UsuarioDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
            String username,
            String password,
            String email,
            boolean isEnabled,
            Rol rol
    ) {}

    public record Response (
            UUID id,
            String username,
            String email,
            boolean isEnabled,
            Rol rol
    ) {
        public static Response fromEntity (Usuario data) {
            return new Response(
                    data.getId(),
                    data.getUsername(),
                    data.getEmail(),
                    data.getIsEnabled(),
                    data.getRol()
            );
        }
    }
}
