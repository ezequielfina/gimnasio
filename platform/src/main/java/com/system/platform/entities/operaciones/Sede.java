package com.system.platform.entities.operaciones;

import com.system.platform.entities.EntityBase;
import com.system.platform.entities.geo.Localidad;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "sedes", schema = "operaciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sede extends EntityBase {
    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @ColumnDefault("'CORE'")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "tipo_sede", columnDefinition = "operaciones.tipo_sede not null")
    private TipoSede tipoSede;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_localidad", nullable = false)
    private Localidad localidad;

    @Size(max = 150)
    @Column(name = "direccion", length = 150)
    private String direccion;
}
