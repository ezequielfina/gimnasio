package com.system.platform.repositories.comercial;

import com.system.platform.entities.comercial.Plan;
import com.system.platform.entities.comercial.PlanValorizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PlanValorizadoRepository extends JpaRepository<PlanValorizado, UUID> {
    List<PlanValorizado> findAllByPlan(Plan plan);

    @Query(value = """
    SELECT * 
    FROM comercial.planes_valorizados pv
    WHERE pv.id_plan = :idPlan
      AND pv.fecha_desde <= COALESCE(:fechaHasta, '9999-12-31'::date)
      AND COALESCE(pv.fecha_hasta, '9999-12-31'::date) >= :fechaDesde
    """, nativeQuery = true)
    List<PlanValorizado> findConChoqueDeFechas(
            @Param("idPlan") UUID idPlan,
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta
    );

}
