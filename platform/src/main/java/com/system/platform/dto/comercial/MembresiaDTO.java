package com.system.platform.dto.comercial;

import com.system.platform.dto.auth.UsuarioDTO;
import com.system.platform.entities.comercial.EstadoMembresia;
import com.system.platform.entities.comercial.Membresia;
import com.system.platform.entities.comercial.TipoPlan;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
public final class MembresiaDTO {
    public record Base (
        UUID id
    ) {}

    public record Create (
            UsuarioDTO.Base usuario,
            PlanValorizadoDTO.Base planValorizado,
            LocalDate fechaCompra,
            LocalDate fechaVencimiento,
            TipoPlan tipoPlan,
            EstadoMembresia estadoMembresia
    ) {}

    public record Update (
            @Nullable UsuarioDTO.Base usuario,
            @Nullable PlanValorizadoDTO.Base planValorizado,
            @Nullable LocalDate fechaCompra,
            @Nullable LocalDate fechaVencimiento,
            @Nullable TipoPlan tipoPlan,
            @Nullable EstadoMembresia estadoMembresia
    ) {}

    public record Response (
            UUID id,
            UsuarioDTO.Response usuario,
            PlanValorizadoDTO.Response planValorizado,
            LocalDate fechaCompra,
            LocalDate fechaVencimiento,
            TipoPlan tipoPlan,
            EstadoMembresia estadoMembresia
    ) {
                public static Response fromEntity (Membresia data) {
                    return new Response(
                            data.getId(),
                            UsuarioDTO.Response.fromEntity(data.getUsuario()),
                            PlanValorizadoDTO.Response.fromEntity(data.getPlanValorizado()),
                            data.getFechaCompra(),
                            data.getFechaVencimiento(),
                            data.getTipo(),
                            data.getEstado()
                    );
                }
    }
}
