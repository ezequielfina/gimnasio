package com.system.platform.entities.comercial;

import com.system.platform.entities.EntityBase;
import com.system.platform.entities.auth.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "membresias", schema = "comercial")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Membresia extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan_valorizado", nullable = false)
    private PlanValorizado planValorizado;

    @NotNull
    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @NotNull
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "tipo", columnDefinition = "tipo_plan not null")
    private TipoPlan tipo;

    @ColumnDefault("'ACTIVA'")
    @Column(name = "estado", columnDefinition = "estado_membresia not null")
    private EstadoMembresia estado;
}
