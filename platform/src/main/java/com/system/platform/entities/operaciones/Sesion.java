package com.system.platform.entities.operaciones;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "sesiones", schema = "operaciones")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sesion extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_clase", nullable = false)
    private Clase clase;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_espacio", nullable = false)
    private Espacio espacio;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ColumnDefault("'PROGRAMADA'")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "estado", columnDefinition = "operaciones.estado_sesion not null")
    private EstadoSesion estado;
}
