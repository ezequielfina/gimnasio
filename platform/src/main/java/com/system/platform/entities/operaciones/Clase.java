package com.system.platform.entities.operaciones;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "clases", schema = "operaciones")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @Column(name = "dia", columnDefinition = "dia not null")
    private Dia dia;

    @NotNull
    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;
}
