package com.system.platform.entities.comercial;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "planes_valorizados", schema = "comercial")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanValorizado extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan", nullable = false)
    private Plan plan;

    @NotNull
    @Column(name = "precio_mensual", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioMensual;

    @NotNull
    @Column(name = "precio_anual", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioAnual;

    @NotNull
    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha_desde", nullable = false)
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;
}
