package com.system.platform.entities.operaciones;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "aperturas_sedes", schema = "operaciones")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AperturaSede extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede sede;

    @Column(name = "dia", columnDefinition = "dia not null")
    private Dia dia;

    @NotNull
    @Column(name = "horario_apertura", nullable = false)
    private LocalTime horarioApertura;

    @Column(name = "horario_clausura")
    private LocalTime horarioClausura;
}
