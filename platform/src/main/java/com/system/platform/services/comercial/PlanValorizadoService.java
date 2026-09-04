package com.system.platform.services.comercial;

import com.system.platform.common.exception.ReglaNegocioException;
import com.system.platform.dto.comercial.PlanValorizadoDTO;
import com.system.platform.entities.comercial.Plan;
import com.system.platform.entities.comercial.PlanValorizado;
import com.system.platform.repositories.comercial.PlanValorizadoRepository;
import com.system.platform.services.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanValorizadoService implements ICrudService<PlanValorizado, PlanValorizadoDTO.Create> {
    private final PlanValorizadoRepository planValorizadoRepository;
    private final PlanService planService;

    @Override
    @Transactional
    public PlanValorizado create(PlanValorizadoDTO.Create data) {
        Plan plan = this.planService.readById(data.plan().id());
        List<PlanValorizado> planValorizados = this
                .planValorizadoRepository
                .findConChoqueDeFechas(
                        data.plan().id(),
                        data.fechaDesde(),
                        data.fechaHasta()
                );

        if (!planValorizados.isEmpty()) {
            throw new ReglaNegocioException("Ya existen valores por fechas indicadas.");
        }

        PlanValorizado planValorizado = PlanValorizado.builder()
                .plan(plan)
                .precioMensual(data.precioMensual())
                .precioAnual(data.precioAnual())
                .fechaDesde(data.fechaDesde())
                .fechaHasta(data.fechaHasta())
                .build();

        return this.planValorizadoRepository.save(planValorizado);
    }

    @Override
    public PlanValorizado readById(UUID id) {
        return this.planValorizadoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Plan valorizado no encontrado con ID " + id)
        );
    }

    @Override
    public List<PlanValorizado> readAll() {
        return this.planValorizadoRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        PlanValorizado planValorizado = this.readById(id);
        this.planValorizadoRepository.delete(planValorizado);
    }
}
