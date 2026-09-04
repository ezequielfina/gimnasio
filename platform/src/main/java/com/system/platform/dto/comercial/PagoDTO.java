package com.system.platform.dto.comercial;

import com.system.platform.entities.comercial.EstadoPago;
import com.system.platform.entities.comercial.MetodoPago;
import com.system.platform.entities.comercial.Pago;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
public final class PagoDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
                 MembresiaDTO.Base membresia,
                 BigDecimal monto,
                 MetodoPago metodo,
                 EstadoPago estadoPago,
                 Instant fechaPago,
                 @Nullable String transaccionId
    ) {}

    public record Response (
            UUID id,
            MembresiaDTO.Response membresia,
            BigDecimal monto,
            MetodoPago metodo,
            EstadoPago estadoPago,
            Instant fechaPago,
            @Nullable String transaccionId
    ) {
        public static Response fromEntity (Pago data) {
            return new Response(
                data.getId(),
                MembresiaDTO.Response.fromEntity(data.getMembresia()),
                data.getMonto(),
                data.getMetodo(),
                data.getEstado(),
                data.getFechaPago(),
                data.getTransaccionId()
            );
        }
    }
}
