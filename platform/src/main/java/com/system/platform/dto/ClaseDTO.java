package com.system.platform.dto;

import com.system.platform.entities.operaciones.Clase;
import com.system.platform.entities.operaciones.Dia;
import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
public class ClaseDTO {
    public record Base (
            UUID id
    ) {}

    public record Create (
            DisciplinaDTO.Base disciplina,
            ProfesorDTO.Base profesor,
            Dia dia,
            LocalTime horarioInicio,
            @Nullable Integer duracionMinutos
    ) {}

    public record Update (
            @Nullable DisciplinaDTO.Base disciplina,
            @Nullable ProfesorDTO.Base profesor,
            @Nullable Dia dia,
            @Nullable LocalTime horarioInicio,
            @Nullable Integer duracionMinutos
    ) {}

    public record Response (
            UUID id,
            DisciplinaDTO.Response disciplina,
            ProfesorDTO.Response profesor,
            Dia dia,
            LocalTime horarioInicio,
            Integer duracionMinutos
    ) {
        public static Response fromEntity (Clase data) {
            return new Response(
                    data.getId(),
                    DisciplinaDTO.Response.fromEntity(data.getDisciplina()),
                    ProfesorDTO.Response.fromEntity(data.getProfesor()),
                    data.getDia(),
                    data.getHorarioInicio(),
                    data.getDuracionMinutos()
            );
        }
    }
}
