package com.system.platform.dto.comercial;

import com.system.platform.entities.comercial.EstadoPago;
import com.system.platform.entities.comercial.MetodoPago;
import lombok.NoArgsConstructor;

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
                 EstadoPago estadoPago

    ) {}
}
