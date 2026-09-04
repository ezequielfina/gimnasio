package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MembresiaRepository extends JpaRepository<Membresia, UUID> {
    @Query(value = """
        SELECT 
                    *
        FROM comercial.membresias as mem
        WHERE mem.id_usuario = :idUsuario and mem.id_plan_valorizado = :idPlanValorizado
                and mem.fecha_vencimiento < :fechaCompra
        """, nativeQuery = true)
    List<Membresia> findByIdUsuarioConChoqueFechas(
            @Param("idUsuario") UUID idUsuario,
            @Param("idPlanValorizado") UUID idPlanValorizado,
            @Param("fechaCompra") LocalDate fechaCompra
            );
}
