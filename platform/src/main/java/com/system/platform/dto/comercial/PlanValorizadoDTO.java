package com.system.platform.dto.comercial;

import com.system.platform.entities.comercial.PlanValorizado;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
public final class PlanValorizadoDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
            PlanDTO.Base plan,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            LocalDate fechaDesde,
            @Nullable LocalDate fechaHasta
    ) {}

    public record Update (
            @Nullable PlanDTO.Base plan,
            @Nullable BigDecimal precioMensual,
            @Nullable BigDecimal precioAnual,
            @Nullable LocalDate fechaDesde,
            @Nullable LocalDate fechaHasta
    ) {}

    public record Response (
            UUID id,
            PlanDTO.Response plan,
            BigDecimal precioMensual,
            BigDecimal precioAnual,
            LocalDate fechaDesde,
            @Nullable LocalDate fechaHasta
    ) {
        public static Response fromEntity(PlanValorizado data) {
            return new Response(
                    data.getId(),
                    PlanDTO.Response.fromEntity(data.getPlan()),
                    data.getPrecioMensual(),
                    data.getPrecioAnual(),
                    data.getFechaDesde(),
                    data.getFechaHasta()
            );
        }
    }
}
