package com.system.platform.dto.operaciones;

import com.system.platform.entities.operaciones.AperturaSede;
import com.system.platform.entities.operaciones.Dia;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
public final class AperturaSedeDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
            SedeDTO.Base sede,
            Dia dia,
            LocalTime horarioApertura,
            LocalTime horarioClausura
    ) {}

    public record Update (
            @Nullable SedeDTO.Base sede,
            @Nullable Dia dia,
            @Nullable LocalTime horarioApertura,
            @Nullable LocalTime horarioClausura
    ) {}

    public record Response (
            UUID id,
            SedeDTO.Response sede,
            Dia dia,
            LocalTime horarioApertura,
            @Nullable LocalTime horarioClausura
    ) {
        public static Response fromEntity (AperturaSede data) {
            return new Response(
                    data.getId(),
                    SedeDTO.Response.fromEntity(data.getSede()),
                    data.getDia(),
                    data.getHorarioApertura(),
                    data.getHorarioClausura()
            );
        }
    }
}
