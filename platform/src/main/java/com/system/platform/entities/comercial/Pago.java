package com.system.platform.entities.comercial;

import com.system.platform.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "pagos", schema = "comercial")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pago extends EntityBase {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_membresia", nullable = false)
    private Membresia membresia;

    @NotNull
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "metodo", columnDefinition = "metodo_pago not null")
    private MetodoPago metodo;

    @ColumnDefault("'PENDIENTE'")
    @Column(name = "estado", columnDefinition = "estado_pago not null")
    private EstadoPago estado;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "fecha_pago", nullable = false)
    private Instant fechaPago;

    @Size(max = 100)
    @Column(name = "transaccion_id", length = 100)
    private String transaccionId;
}
